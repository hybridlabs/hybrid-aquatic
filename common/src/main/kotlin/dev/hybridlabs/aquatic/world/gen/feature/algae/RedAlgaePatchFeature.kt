package dev.hybridlabs.aquatic.world.gen.feature.algae

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.TallSeagrassBlock
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration
import kotlin.math.sqrt

class RedAlgaePatchFeature(codec: Codec<ProbabilityFeatureConfiguration>) :
    Feature<ProbabilityFeatureConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<ProbabilityFeatureConfiguration>): Boolean {
        val level = context.level()
        val random = context.random()
        val origin = context.origin()
        val probabilityConfig = context.config() as ProbabilityFeatureConfiguration

        val radius = 4 + random.nextInt(6)
        val attempts = 3 + random.nextInt(2)

        var placedAny = false

        repeat(attempts) {
            val dx = random.nextInt(radius) - random.nextInt(radius)
            val dz = random.nextInt(radius) - random.nextInt(radius)

            val dist = sqrt((dx * dx + dz * dz).toDouble()) / radius.toDouble()

            val radialChance = 1.0 - dist * 0.9
            if (random.nextDouble() > radialChance) return@repeat

            val x = origin.x + dx
            val z = origin.z + dz
            val y = level.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z)
            val pos = BlockPos(x, y, z)

            if (!level.getBlockState(pos).`is`(Blocks.WATER)) return@repeat

            if (random.nextDouble() > probabilityConfig.probability) return@repeat

            val state = when {
                dist < 0.30 && random.nextFloat() < 0.75f ->
                    HybridAquaticBlocks.TALL_RED_ALGAE.get().defaultBlockState()
                dist < 0.65 ->
                    HybridAquaticBlocks.RED_ALGAE.get().defaultBlockState()
                else ->
                    HybridAquaticBlocks.SHORT_RED_ALGAE.get().defaultBlockState()
            }

            if (!state.canSurvive(level, pos)) return@repeat

            if (state.`is`(HybridAquaticBlocks.TALL_RED_ALGAE.get())) {
                val above = pos.above()
                if (level.getBlockState(above).`is`(Blocks.WATER)) {
                    level.setBlock(pos, state, 2)
                    level.setBlock(
                        above,
                        state.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER),
                        2
                    )
                    placedAny = true
                }
            } else {
                level.setBlock(pos, state, 2)
                placedAny = true
            }
        }

        return placedAny
    }
}