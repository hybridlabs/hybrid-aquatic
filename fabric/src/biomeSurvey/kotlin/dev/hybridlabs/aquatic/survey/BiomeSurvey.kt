package dev.hybridlabs.aquatic.survey

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import java.nio.file.Files
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.BlockPos
import net.minecraft.core.QuartPos
import net.minecraft.resources.ResourceKey
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Heightmap
import org.apache.logging.log4j.LogManager

/**
 * Samples the overworld biome source on a fixed grid once the server has started, writes a report and stops the
 * server. The process exits with status 1 when a check fails, so `runBiomeSurvey` fails the build.
 *
 * Sampling goes through the level's real biome source, so every mod that hooks or wraps it (Biolith, TerraBlender,
 * Lithostitched) takes part exactly as it would in a live world.
 *
 * Surface rules are checked by generating a few sampled columns per biome and looking for the biome's floor block
 * near the top of each. Features such as coral can bury the floor, so one matching column is enough.
 *
 * Only presence is checked, not amounts: Biolith orders its replacements per new world (then saves that order), so a
 * fresh world with the same seed can split a target biome between its replacements differently.
 */
object BiomeSurvey : ModInitializer {
    private const val RADIUS = 300
    private const val STEP = 96
    private val LAYERS = intArrayOf(50, 0, -40)
    private const val SURFACE_Y = 50

    /** How far, in blocks, a tide pool may sit from a lukewarm ocean and still count as bordering one. */
    private const val NEIGHBOUR_REACH = 64
    private const val MIN_TIDE_POOLS_BY_LUKEWARM = 0.9

    private val LUKEWARM = setOf(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN)

    /** Columns generated per biome when checking its floor block. */
    private const val FLOOR_SAMPLES = 8

    /** How many blocks below the ocean floor heightmap the floor block may be buried by features. */
    private const val FLOOR_DEPTH = 8

    /** Floor blocks that only our surface rules place in these biomes. */
    private val FLOORS: Map<ResourceKey<Biome>, () -> Block> = mapOf(
        HABiomes.TIDE_POOLS to { HABlocks.SHORESTONE.get() },
        HABiomes.CORAL_REEF to { HABlocks.SHORESTONE.get() },
        HABiomes.SEAGRASS_BED to { HABlocks.GRASSY_SAND.get() },
        HABiomes.TROPICAL_RIVER to { Blocks.MUD },
        HABiomes.TRENCH to { HABlocks.MARINE_SNOW.get() },
        HABiomes.WARM_TRENCH to { HABlocks.MARINE_SNOW.get() },
        HABiomes.LUKEWARM_TRENCH to { HABlocks.MARINE_SNOW.get() },
        HABiomes.COLD_TRENCH to { HABlocks.MARINE_SNOW.get() },
        HABiomes.FROZEN_TRENCH to { HABlocks.MARINE_SNOW.get() },
    )

    private var failed = false

    override fun onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(::survey)
        ServerLifecycleEvents.SERVER_STOPPED.register {
            if (failed) {
                LogManager.shutdown()
                Runtime.getRuntime().halt(1)
            }
        }
    }

    private fun survey(server: MinecraftServer) {
        val level = server.overworld()
        val source = level.chunkSource.generator.biomeSource
        val sampler = level.chunkSource.randomState().sampler()

        fun biomeAt(x: Int, y: Int, z: Int): ResourceKey<Biome> =
            source.getNoiseBiome(QuartPos.fromBlock(x), QuartPos.fromBlock(y), QuartPos.fromBlock(z), sampler)
                .unwrapKey().orElseThrow()

        val counts = HashMap<ResourceKey<Biome>, Int>()
        var tidePools = 0
        var tidePoolsByLukewarm = 0
        val floorColumns = HashMap<ResourceKey<Biome>, MutableList<BlockPos>>()

        for (i in -RADIUS..RADIUS) for (j in -RADIUS..RADIUS) {
            val x = i * STEP
            val z = j * STEP
            for (y in LAYERS) {
                val biome = biomeAt(x, y, z)
                counts.merge(biome, 1, Int::plus)

                if (biome in FLOORS) {
                    val columns = floorColumns.getOrPut(biome, ::mutableListOf)
                    val column = BlockPos(x, 0, z)
                    if (columns.size < FLOOR_SAMPLES && column !in columns) columns.add(column)
                }

                if (y == SURFACE_Y && biome == HABiomes.TIDE_POOLS) {
                    tidePools++
                    if (bordersLukewarm(x, z, ::biomeAt)) tidePoolsByLukewarm++
                }
            }
        }

        val expected = source.possibleBiomes()
            .mapNotNull { it.unwrapKey().orElse(null) }
            .filter { it.location().namespace == Constants.MOD_ID }
            .sortedBy { it.location().path }
        val missing = expected.filter { (counts[it] ?: 0) == 0 }
        val floors = floorColumns.mapValues { (biome, columns) -> floorMatches(level, biome, columns) }
        val lukewarmShare = if (tidePools == 0) 0.0 else tidePoolsByLukewarm.toDouble() / tidePools

        val failures = buildList {
            if (expected.isEmpty()) add("biome source lists no ${Constants.MOD_ID} biomes")
            missing.forEach { add("never sampled: ${it.location()}") }
            floors.forEach { (biome, matches) ->
                if (matches == 0) add("no ${FLOORS.getValue(biome)().builtInRegistryHolder().key().location()} floor in ${biome.location()}")
            }
            if (lukewarmShare < MIN_TIDE_POOLS_BY_LUKEWARM) {
                add("only ${percent(lukewarmShare)} of tide pools border a lukewarm ocean")
            }
        }
        failed = failures.isNotEmpty()

        val mods = FabricLoader.getInstance().allMods
            .map { it.metadata }
            .filter { it.id in setOf("biolith", "lithostitched", "terrablender", "biomesoplenty") }
            .joinToString { "${it.id} ${it.version.friendlyString}" }
        val report = buildString {
            appendLine("Biome survey, seed ${level.seed}, ${2 * RADIUS + 1}^2 columns ${STEP} blocks apart at y ${LAYERS.joinToString()}")
            appendLine("Worldgen mods: ${mods.ifEmpty { "none" }}")
            appendLine("Biome source: ${source.javaClass.name}")
            appendLine()
            expected.forEach { appendLine("${it.location()}: ${counts[it] ?: 0}") }
            appendLine()
            listOf(Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.BEACH).forEach {
                appendLine("${it.location()}: ${counts[it] ?: 0}")
            }
            floors.toSortedMap(compareBy { it.location().path }).forEach { (biome, matches) ->
                appendLine("${biome.location()} floor: $matches / ${floorColumns.getValue(biome).size} columns")
            }
            appendLine("tide pools bordering lukewarm ocean: $tidePoolsByLukewarm / $tidePools (${percent(lukewarmShare)})")
            appendLine()
            appendLine(if (failed) "FAILED" else "PASSED")
            failures.forEach { appendLine("  $it") }
        }

        Files.writeString(server.serverDirectory.resolve("biome-survey-report.txt"), report)
        Constants.LOGGER.info("Biome survey report\n{}", report)
        server.halt(false)
    }

    /**
     * Counts the columns whose floor lies in [biome] and is made of the biome's floor block. Generates each column's
     * chunk.
     */
    private fun floorMatches(level: ServerLevel, biome: ResourceKey<Biome>, columns: List<BlockPos>): Int {
        val floor = FLOORS.getValue(biome)()
        return columns.count { column ->
            level.getChunk(column)
            val top = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, column).below()
            level.getBiome(top).`is`(biome) && (0 until FLOOR_DEPTH).any { level.getBlockState(top.below(it)).`is`(floor) }
        }
    }

    private fun bordersLukewarm(x: Int, z: Int, biomeAt: (Int, Int, Int) -> ResourceKey<Biome>): Boolean {
        for (distance in 16..NEIGHBOUR_REACH step 16) {
            for (dx in -1..1) for (dz in -1..1) {
                if ((dx != 0 || dz != 0) && biomeAt(x + dx * distance, SURFACE_Y, z + dz * distance) in LUKEWARM) {
                    return true
                }
            }
        }
        return false
    }

    private fun percent(share: Double) = "%.1f%%".format(share * 100)
}
