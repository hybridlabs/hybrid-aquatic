package dev.hybridlabs.aquatic.world.gen.feature

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

class RedAlgaeFeature(codec: Codec<ProbabilityFeatureConfiguration>) :
    Feature<ProbabilityFeatureConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<ProbabilityFeatureConfiguration>): Boolean {
        var placed = false
        val random = context.random()
        val level = context.level()
        val origin = context.origin()
        val config = context.config()

        val i = random.nextInt(8) - random.nextInt(8)
        val j = random.nextInt(8) - random.nextInt(8)
        val y = level.getHeight(Heightmap.Types.OCEAN_FLOOR, origin.x + i, origin.z + j)
        val pos = BlockPos(origin.x + i, y, origin.z + j)

        if (!level.getBlockState(pos).`is`(Blocks.WATER)) return false

        val tall = random.nextDouble() < config.probability
        val short = !tall && random.nextFloat() < 0.35f

        val state = when {
            tall -> HybridAquaticBlocks.TALL_RED_ALGAE.get().defaultBlockState()
            short -> HybridAquaticBlocks.SHORT_RED_ALGAE.get().defaultBlockState()
            else -> HybridAquaticBlocks.RED_ALGAE.get().defaultBlockState()
        }

        if (!state.canSurvive(level, pos)) return false

        if (tall) {
            val above = pos.above()
            if (level.getBlockState(above).`is`(Blocks.WATER)) {
                val upper = state.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER)
                level.setBlock(pos, state, 2)
                level.setBlock(above, upper, 2)
                placed = true
            }
        } else {
            level.setBlock(pos, state, 2)
            placed = true
        }

        return placed
    }
}