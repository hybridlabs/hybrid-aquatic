package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.TallSeagrassBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.ProbabilityConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

class SeaLettuceFeature(codec: Codec<ProbabilityConfig>) :
    Feature<ProbabilityConfig>(codec) {
    override fun generate(context: FeatureContext<ProbabilityConfig>): Boolean {
        var bl = false
        val random = context.random
        val structureWorldAccess = context.world
        val blockPos = context.origin
        val probabilityConfig = context.config as ProbabilityConfig
        val i = random.nextInt(8) - random.nextInt(8)
        val j = random.nextInt(8) - random.nextInt(8)
        val k = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, blockPos.x + i, blockPos.z + j)
        val blockPos2 = BlockPos(blockPos.x + i, k, blockPos.z + j)
        if (structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER)) {
            val bl2 = random.nextDouble() < probabilityConfig.probability.toDouble()
            val blockState = if (bl2) {
                HybridAquaticBlocks.TALL_SEA_LETTUCE.defaultState
            } else {
                HybridAquaticBlocks.SEA_LETTUCE.defaultState
            }
            if (blockState.canPlaceAt(structureWorldAccess, blockPos2)) {
                if (bl2) {
                    val blockState2 = blockState.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER) as BlockState
                    val blockPos3 = blockPos2.up()
                    if (structureWorldAccess.getBlockState(blockPos3).isOf(Blocks.WATER)) {
                        structureWorldAccess.setBlockState(blockPos2, blockState, 2)
                        structureWorldAccess.setBlockState(blockPos3, blockState2, 2)
                    }
                } else {
                    structureWorldAccess.setBlockState(blockPos2, blockState, 2)
                }

                bl = true
            }
        }

        return bl
    }
}