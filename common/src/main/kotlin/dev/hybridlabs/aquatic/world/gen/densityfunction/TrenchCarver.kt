package dev.hybridlabs.aquatic.world.gen.densityfunction

import com.google.gson.JsonElement
import com.mojang.serialization.JsonOps
import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.mixin.HolderReferenceInvoker
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.RegistryOps
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.util.KeyDispatchDataCodec
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.DensityFunction
import net.minecraft.world.level.levelgen.DensityFunctions
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import net.minecraft.world.level.levelgen.NoiseRouter

/**
 * Carves trenches into the overworld's noise router before the server loads its levels.
 *
 * When the router reads `minecraft:overworld/offset`, the trench depth wrapper already shapes the trench, so only
 * `hybrid_aquatic:trench/cleanup_carver` runs, clearing terrain that mods like Terralith add inside it. Otherwise, as
 * with Tectonic, `hybrid_aquatic:trench/carver` carves the whole trench and `hybrid_aquatic:trench/biome_depth` makes
 * its floor count as surface, so it gets the trench biomes instead of cave biomes.
 *
 * Carvers apply as a minimum over the router's final and initial density, so they only ever remove terrain. Inside them,
 * `{"type": "hybrid_aquatic:continents_marker"}` stands for the router's continentalness, the same value Biolith places
 * the trench biomes by, so the carved trench always lines up with the trench biomes.
 */
object TrenchCarver {
    private val CARVER = ResourceKey.create(Registries.DENSITY_FUNCTION, CommonClass.locate("trench/carver"))
    private val BIOME_DEPTH = ResourceKey.create(Registries.DENSITY_FUNCTION, CommonClass.locate("trench/biome_depth"))
    private val CLEANUP_CARVER = ResourceKey.create(Registries.DENSITY_FUNCTION, CommonClass.locate("trench/cleanup_carver"))
    private val VANILLA_OFFSET = ResourceLocation.withDefaultNamespace("overworld/offset")

    private val DENSITY_FUNCTION_TYPES = CommonClass.DENSITY_FUNCTION_TYPES
    val CONTINENTS_MARKER = DENSITY_FUNCTION_TYPES.register("continents_marker") { ContinentsMarker.CODEC.codec() }

    @JvmStatic
    fun apply(server: MinecraftServer) {
        val overworld = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM).get(LevelStem.OVERWORLD) ?: return
        val generator = overworld.generator() as? NoiseBasedChunkGenerator ?: return

        // Inline settings have no registry holder to rebind, which only happens with hand-written world presets
        val settings = generator.generatorSettings() as? Holder.Reference<NoiseGeneratorSettings> ?: run {
            Constants.LOGGER.warn("Overworld noise settings are not registered, so trenches will not be carved")
            return
        }

        val router = settings.value().noiseRouter()
        val densityFunctions = server.registryAccess().registryOrThrow(Registries.DENSITY_FUNCTION)
        fun load(key: ResourceKey<DensityFunction>) = densityFunctions.getHolderOrThrow(key).value()
            .mapAll { function -> if (function is ContinentsMarker) router.continents() else function }

        val offsetShapesTrench = reads(router.finalDensity(), VANILLA_OFFSET, server)
        Constants.LOGGER.info(if (offsetShapesTrench) "Trenches follow the overworld offset" else "Overworld terrain skips the offset, so trenches are carved directly")
        val carver = load(if (offsetShapesTrench) CLEANUP_CARVER else CARVER)
        val depth = if (offsetShapesTrench) router.depth() else DensityFunctions.min(router.depth(), load(BIOME_DEPTH))

        val carvedRouter = NoiseRouter(
            router.barrierNoise(),
            router.fluidLevelFloodednessNoise(),
            router.fluidLevelSpreadNoise(),
            router.lavaNoise(),
            router.temperature(),
            router.vegetation(),
            router.continents(),
            router.erosion(),
            depth,
            router.ridges(),
            // Initial density sets the preliminary surface, which aquifers use to flood the trench with sea water
            DensityFunctions.min(router.initialDensityWithoutJaggedness(), carver),
            DensityFunctions.min(router.finalDensity(), carver),
            router.veinToggle(),
            router.veinRidged(),
            router.veinGap(),
        )

        val current = settings.value()
        @Suppress("DEPRECATION")
        val carved = NoiseGeneratorSettings(
            current.noiseSettings(),
            current.defaultBlock(),
            current.defaultFluid(),
            carvedRouter,
            current.surfaceRule(),
            current.spawnTarget(),
            current.seaLevel(),
            current.disableMobGeneration(),
            current.isAquifersEnabled(),
            current.oreVeinsEnabled(),
            current.useLegacyRandomSource(),
        )

        @Suppress("UNCHECKED_CAST")
        (settings as HolderReferenceInvoker<NoiseGeneratorSettings>).`hybridAquatic$bindValue`(carved)
    }

    /**
     * Whether [function] references the registered density function [target], following references through the registry.
     */
    private fun reads(function: DensityFunction, target: ResourceLocation, server: MinecraftServer): Boolean {
        val ops = RegistryOps.create(JsonOps.INSTANCE, server.registryAccess())
        val densityFunctions = server.registryAccess().registryOrThrow(Registries.DENSITY_FUNCTION)
        val visited = mutableSetOf<ResourceLocation>()
        val pending = ArrayDeque(listOf(function))

        while (pending.isNotEmpty()) {
            // References to registered functions encode as their ID, so every string in the encoding is a candidate
            val json = DensityFunction.HOLDER_HELPER_CODEC.encodeStart(ops, pending.removeFirst()).result().orElse(null) ?: continue
            for (id in strings(json).mapNotNull(ResourceLocation::tryParse)) {
                if (id == target) return true
                if (visited.add(id)) densityFunctions.get(id)?.let(pending::add)
            }
        }

        return false
    }

    private fun strings(json: JsonElement): Sequence<String> = when {
        json.isJsonPrimitive -> if (json.asJsonPrimitive.isString) sequenceOf(json.asString) else emptySequence()
        json.isJsonArray -> json.asJsonArray.asSequence().flatMap(::strings)
        json.isJsonObject -> json.asJsonObject.entrySet().asSequence().flatMap { strings(it.value) }
        else -> emptySequence()
    }

    /**
     * Placeholder for the router's continentalness. [apply] replaces every marker, so it never reaches world generation.
     */
    object ContinentsMarker : DensityFunction.SimpleFunction {
        val CODEC: KeyDispatchDataCodec<ContinentsMarker> = KeyDispatchDataCodec.of(MapCodec.unit(ContinentsMarker))

        override fun compute(context: DensityFunction.FunctionContext): Double = 0.0
        override fun minValue(): Double = 0.0
        override fun maxValue(): Double = 0.0
        override fun codec(): KeyDispatchDataCodec<out DensityFunction> = CODEC
    }
}
