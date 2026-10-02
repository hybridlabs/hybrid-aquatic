package dev.hybridlabs.aquatic.world.gen.biome

import com.google.common.base.Supplier
import com.google.common.base.Suppliers
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.access.PlacedBiomeSource
import dev.hybridlabs.aquatic.mixin.BiomeSourceAccessor
import dev.hybridlabs.aquatic.mixin.MultiNoiseBiomeSourceInvoker
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.utils.OpenSimplex2
import dev.hybridlabs.aquatic.world.gen.OverworldGenerator
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.NbtIo
import net.minecraft.nbt.Tag
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.tags.TagKey
import net.minecraft.util.Mth
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Climate
import net.minecraft.world.level.biome.MultiNoiseBiomeSource
import net.minecraft.world.level.storage.LevelResource
import java.io.IOException
import java.util.Random

/**
 * Places the mod's biomes in the overworld before the server loads its levels.
 *
 * Placement generates the same world Biolith did, which the mod used before. A replacement gives a share of its target
 * biome to another biome, split along a seeded noise. A sub-biome then takes over wherever the biome that results
 * meets all of its criteria. Biolith saved the order it laid a target's replacements out in with each world, so worlds
 * it generated keep that order and their biomes carry on across chunk borders.
 *
 * When another mod brings Biolith along, the placement is handed to it instead. Biolith splits one noise range between
 * every mod's replacements of a biome, so placing ours apart from it would have them compete for the same range.
 */
object HABiomePlacement {
    private val BIOLITH_VANILLA = ResourceLocation("biolith", "vanilla")
    private const val BIOLITH_STATE = "data/biolith_overworld_state.dat"

    private val biolithLoaded by lazy { Services.PLATFORM.isModLoaded("biolith") }

    /**
     * Registers the placement with Biolith when it is installed. Without it, [apply] places the biomes.
     */
    @JvmStatic
    fun register() {
        if (!HABiomes.config.config.biomeConfig.enableBiomes || !biolithLoaded) return

        BiolithPlacement.register(Builder().also(HABiomes::addBiomes))
    }

    @JvmStatic
    fun apply(server: MinecraftServer) {
        if (!HABiomes.config.config.biomeConfig.enableBiomes || biolithLoaded) return

        val generator = OverworldGenerator.find(server) ?: return
        val source = generator.biomeSource as? MultiNoiseBiomeSource ?: return

        val biomes = server.registryAccess().registryOrThrow(Registries.BIOME)
        val seed = server.worldData.worldGenOptions().seed()
        val builder = Builder().also(HABiomes::addBiomes)

        // Targets from mods that are not installed have no biome to replace
        val savedOrder = savedReplacementOrder(server)
        val replacements = buildMap<Holder<Biome>, List<Replacement>> {
            for ((target, requests) in builder.replacements) {
                biomes.getHolder(target).ifPresent { put(it, layOut(target, requests, biomes, seed, savedOrder[target.location()])) }
            }
        }

        // Sub-biomes of one target are tried in the order of their IDs
        val subBiomes = buildMap<Holder<Biome>, List<SubBiome>> {
            for ((target, requests) in builder.subBiomes) {
                val ordered = requests.sortedBy { it.biome.location() }.map { SubBiome(biomes.getHolderOrThrow(it.biome), it.criteria) }
                biomes.getHolder(target).ifPresent { put(it, ordered) }
            }
        }

        val parameters = (source as MultiNoiseBiomeSourceInvoker).`hybridAquatic$parameters`()
        (source as PlacedBiomeSource).setBiomePlacement(Placement(replacements, subBiomes, Leaves(parameters), seed))

        // Features, structures and /locate only know the biomes a source lists
        val accessor = source as BiomeSourceAccessor
        val possibleBiomes = accessor.`hybridAquatic$getPossibleBiomes`()
        val placedBiomes = replacements.values.flatten().mapNotNull(Replacement::biome) + subBiomes.values.flatten().map(SubBiome::biome)
        accessor.`hybridAquatic$setPossibleBiomes`(Suppliers.memoize(Supplier { possibleBiomes.get() + placedBiomes }))
    }

    /**
     * Splits the noise range between a target's replacements. The target keeps the share its largest replacement leaves.
     */
    private fun layOut(
        target: ResourceKey<Biome>,
        requests: List<ReplacementRequest>,
        biomes: Registry<Biome>,
        seed: Long,
        savedOrder: List<ResourceLocation>?,
    ): List<Replacement> {
        val shares = requests.mapTo(mutableListOf()) { it.biome.location() to Mth.clamp(it.rate, 0.0, 1.0) }
        val vanilla = Mth.clamp(1.0 - shares.maxOf { it.second }, 0.0, 1.0)
        val scale = shares.sumOf { it.second } + vanilla
        if (vanilla > 0.0) shares.add(BIOLITH_VANILLA to vanilla)

        // Without a saved order, each replacement takes a seeded position that adding or removing others does not move
        shares.sortBy { (biome, _) -> Random(seed xor "${target.location()}/$biome".hashCode().toLong()).nextLong() }
        if (savedOrder != null) {
            shares.sortBy { (biome, _) -> savedOrder.indexOf(biome).let { if (it < 0) Int.MAX_VALUE else it } }
        }

        var end = 0.0
        return shares.map { (biome, share) ->
            end += share / scale
            Replacement(if (biome == BIOLITH_VANILLA) null else biomes.getHolderOrThrow(ResourceKey.create(Registries.BIOME, biome)), end)
        }
    }

    /**
     * The order Biolith saved each target's replacements in, when it generated this world.
     */
    private fun savedReplacementOrder(server: MinecraftServer): Map<ResourceLocation, List<ResourceLocation>> {
        val file = server.getWorldPath(LevelResource.ROOT).resolve(BIOLITH_STATE).toFile()
        if (!file.isFile) return emptyMap()

        return try {
            NbtIo.readCompressed(file).getCompound("data").getList("BiomeReplacementsList", Tag.TAG_LIST.toInt())
                .map { list -> (list as ListTag).mapNotNull { ResourceLocation.tryParse(it.asString) } }
                .filter { it.isNotEmpty() }
                .associate { it.first() to it.drop(1) }
        } catch (exception: IOException) {
            Constants.LOGGER.warn("Could not read the biome replacement order Biolith saved, so new chunks may not line up with old ones", exception)
            emptyMap()
        }
    }

    class Builder {
        internal val replacements = LinkedHashMap<ResourceKey<Biome>, MutableList<ReplacementRequest>>()
        internal val subBiomes = LinkedHashMap<ResourceKey<Biome>, MutableList<SubBiomeRequest>>()

        /**
         * Replaces [rate] of [target] with [biome].
         */
        fun replaceOverworld(target: ResourceKey<Biome>, biome: ResourceKey<Biome>, rate: Double) {
            replacements.getOrPut(target, ::mutableListOf).add(ReplacementRequest(biome, rate))
        }

        /**
         * Replaces [target] with [biome] wherever all [criteria] are met.
         */
        fun addSubOverworld(target: ResourceKey<Biome>, biome: ResourceKey<Biome>, vararg criteria: Criterion) {
            subBiomes.getOrPut(target, ::mutableListOf).add(SubBiomeRequest(biome, criteria.toList()))
        }
    }

    internal class ReplacementRequest(val biome: ResourceKey<Biome>, val rate: Double)

    internal class SubBiomeRequest(val biome: ResourceKey<Biome>, val criteria: List<Criterion>)

    /**
     * A condition on the position a biome is sampled at, before any replacement.
     */
    sealed interface Criterion {
        fun matches(sample: Sample): Boolean

        /**
         * The next best fitting biome at the position is [biome], or is in [tag].
         */
        class Neighbor private constructor(val biome: ResourceKey<Biome>?, val tag: TagKey<Biome>?) : Criterion {
            constructor(biome: ResourceKey<Biome>) : this(biome, null)
            constructor(tag: TagKey<Biome>) : this(null, tag)

            override fun matches(sample: Sample): Boolean {
                val neighbor = sample.fit.neighbor ?: return false
                return (biome != null && neighbor.`is`(biome)) || (tag != null && neighbor.`is`(tag))
            }
        }

        /**
         * How far the position is from the edge of its biome, from 0 at the edge towards 1 at the center.
         */
        class Edge(val min: Float, val max: Float) : Criterion {
            override fun matches(sample: Sample): Boolean = sample.fit.edge in min..max
        }

        class Continentalness(val min: Float, val max: Float) : Criterion {
            override fun matches(sample: Sample): Boolean = Climate.unquantizeCoord(sample.climate.continentalness()) in min..max
        }

        class Depth(val min: Float, val max: Float) : Criterion {
            override fun matches(sample: Sample): Boolean = Climate.unquantizeCoord(sample.climate.depth()) in min..max
        }
    }

    class Sample internal constructor(private val leaves: Leaves, private val biome: Holder<Biome>, val climate: Climate.TargetPoint) {
        internal val fit by lazy(LazyThreadSafetyMode.NONE) { leaves.fit(biome, climate) }
    }

    internal class Fit(val neighbor: Holder<Biome>?, val edge: Float)

    /**
     * The parameter points of a biome source, flattened to search for how well a biome and its neighbor fit a position.
     */
    internal class Leaves(parameters: Climate.ParameterList<Holder<Biome>>) {
        private val biomes: Array<Holder<Biome>> = parameters.values().map { it.second }.toTypedArray()
        private val min = LongArray(biomes.size * DIMENSIONS)
        private val max = LongArray(biomes.size * DIMENSIONS)

        init {
            parameters.values().forEachIndexed { leaf, pair ->
                val point = pair.first
                val ranges = listOf(point.temperature(), point.humidity(), point.continentalness(), point.erosion(), point.depth(), point.weirdness())
                ranges.forEachIndexed { dimension, range ->
                    min[leaf * DIMENSIONS + dimension] = range.min()
                    max[leaf * DIMENSIONS + dimension] = range.max()
                }
                min[leaf * DIMENSIONS + ranges.size] = point.offset()
                max[leaf * DIMENSIONS + ranges.size] = point.offset()
            }
        }

        /**
         * Finds the closest parameter point of a biome other than [biome], and how close it comes to that of [biome].
         */
        fun fit(biome: Holder<Biome>, climate: Climate.TargetPoint): Fit {
            val target = longArrayOf(climate.temperature(), climate.humidity(), climate.continentalness(), climate.erosion(), climate.depth(), climate.weirdness(), 0L)
            var ownDistance = Long.MAX_VALUE
            var neighborDistance = Long.MAX_VALUE
            var neighbor: Holder<Biome>? = null

            for (leaf in biomes.indices) {
                val own = biomes[leaf] === biome
                val limit = if (own) ownDistance else neighborDistance

                // Squared distance to the leaf, given up on once it can no longer be the closest
                var distance = 0L
                var dimension = 0
                while (dimension < DIMENSIONS && distance < limit) {
                    val index = leaf * DIMENSIONS + dimension
                    val above = target[dimension] - max[index]
                    val below = min[index] - target[dimension]
                    val offset = if (above > 0L) above else if (below > 0L) below else 0L
                    distance += offset * offset
                    dimension++
                }

                if (distance < limit) {
                    if (own) {
                        ownDistance = distance
                    } else {
                        neighborDistance = distance
                        neighbor = biomes[leaf]
                    }
                }
            }

            val edge = when {
                neighbor == null -> 1f
                neighborDistance == 0L -> 0f
                else -> (neighborDistance - ownDistance).toFloat() / neighborDistance.toFloat()
            }

            return Fit(neighbor, edge)
        }

        private companion object {
            const val DIMENSIONS = 7
        }
    }

    internal class Replacement(val biome: Holder<Biome>?, val end: Double)

    internal class SubBiome(val biome: Holder<Biome>, val criteria: List<Criterion>)

    /**
     * The placement of one biome source, grouped by the biome it replaces.
     */
    class Placement internal constructor(
        private val replacements: Map<Holder<Biome>, List<Replacement>>,
        private val subBiomes: Map<Holder<Biome>, List<SubBiome>>,
        private val leaves: Leaves,
        private val seed: Long,
    ) {
        private val offsets = IntArray(8) { (seed shr (it * 8) and 0xffL).toInt() }

        fun place(original: Holder<Biome>, x: Int, y: Int, z: Int, sampler: Climate.Sampler): Holder<Biome> {
            var biome = original
            replacements[original]?.let { replacements ->
                val noise = replacementNoise(x, z)
                biome = replacements.firstOrNull { it.end > noise }?.biome ?: original
            }

            val subBiomes = subBiomes[biome] ?: return biome
            val sample = Sample(leaves, original, sampler.sample(x, y, z))
            return subBiomes.firstOrNull { subBiome -> subBiome.criteria.all { it.matches(sample) } }?.biome ?: biome
        }

        // Four octaves to give the replacements fuzzy edges, brought back to 0..1
        private fun replacementNoise(x: Int, z: Int): Double {
            var noise = OpenSimplex2.noise2(seed, (x + offsets[0]) / 1024.0, (z + offsets[1]) / 1024.0).toDouble()
            noise += OpenSimplex2.noise2(seed, (x + offsets[2]) / 256.0, (z + offsets[3]) / 256.0) / 8.0
            noise += OpenSimplex2.noise2(seed, (x + offsets[4]) / 64.0, (z + offsets[5]) / 64.0) / 16.0
            noise += OpenSimplex2.noise2(seed, (x + offsets[6]) / 16.0, (z + offsets[7]) / 16.0) / 32.0

            return Mth.clamp(noise / 1.21875 * 0.5375 + 0.5, 0.0, 1.0)
        }
    }
}
