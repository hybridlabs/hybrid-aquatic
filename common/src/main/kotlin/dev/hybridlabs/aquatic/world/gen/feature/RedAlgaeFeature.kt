package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.TallSeagrassBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

class RedAlgaeFeature(codec: Codec<ProbabilityFeatureConfiguration>) :
    Feature<ProbabilityFeatureConfiguration>(codec) {
    override fun place(context: FeaturePlaceContext<ProbabilityFeatureConfiguration>): Boolean {
        var bl = false
        val random = context.random()()()
        val structureLevelAccessor = context.level()
        val blockPos = context.origin()()()
        val probabilityConfig = context.config() as ProbabilityFeatureConfiguration
        val i = random.nextInt(8) - random.nextInt(8)
        val j = random.nextInt(8) - random.nextInt(8)
        val k = structureLevelAccessor.getHeight(Heightmap.Types.OCEAN_FLOOR, blockPos.x + i, blockPos.z + j)
        val blockPos2 = BlockPos(blockPos.x + i, k, blockPos.z + j)
        if (structureLevelAccessor.getBlockState(blockPos2).`is`(Blocks.WATER)) {
            val bl2 = random.nextDouble() < probabilityConfig.probability.toDouble()
            val blockState = if (bl2) {
                HybridAquaticBlocks.TALL_RED_ALGAE.get().defaultBlockState()
            } else {
                HybridAquaticBlocks.RED_ALGAE.get().defaultBlockState()
            }
            if (blockState.canSurvive(structureLevelAccessor, blockPos2)) {
                if (bl2) {
                    val blockState2 = blockState.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER) as BlockState
                    val blockPos3 = blockPos2.above()
                    if (structureLevelAccessor.getBlockState(blockPos3).`is`(Blocks.WATER)) {
                        structureLevelAccessor.setBlock(blockPos2, blockState, 2)
                        structureLevelAccessor.setBlock(blockPos3, blockState2, 2)
                    }
                } else {
                    structureLevelAccessor.setBlock(blockPos2, blockState, 2)
                }

                bl = true
            }
        }

        return bl
    }
}