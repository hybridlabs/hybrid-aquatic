package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.KelpBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

class BullKelpFeature(codec: Codec<BullKelpFeatureConfig>) :
    Feature<BullKelpFeatureConfig>(codec) {
    override fun generate(context: FeatureContext<BullKelpFeatureConfig>): Boolean {
        var i = 0
        val structureLevelAccessor = context.world
        val blockPos = context.origin
        val random = context.random
        val j = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, blockPos.x, blockPos.z)
        var blockPos2 = BlockPos(blockPos.x, j, blockPos.z)
        if (structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER)) {
            val blockState = HybridAquaticBlocks.BULL_KELP.defaultBlockState()
            val blockState2 = HybridAquaticBlocks.BULL_KELP_PLANT.defaultBlockState()
            val k = 1 + random.nextInt(10)

            for (l in 0..k) {
                if (structureWorldAccess.getBlockState(blockPos2)
                        .isOf(Blocks.WATER) && structureWorldAccess.getBlockState(blockPos2.up())
                        .isOf(Blocks.WATER) && blockState2.canSurvive(structureLevelAccessor, blockPos2)
                ) {
                    if (l == k) {
                        structureWorldAccess.setBlockState(
                            blockPos2,
                            blockState.with(KelpBlock.AGE, random.nextInt(4) + 20) as BlockState,
                            2
                        )
                        ++i
                    } else {
                        structureWorldAccess.setBlockState(blockPos2, blockState2, 2)
                    }
                } else if (l > 0) {
                    val blockPos3 = blockPos2.down()
                    if (blockState.canSurvive(structureLevelAccessor, blockPos3) && !structureWorldAccess.getBlockState(
                            blockPos3.down()
                        ).isOf(HybridAquaticBlocks.BULL_KELP)
                    ) {
                        structureWorldAccess.setBlockState(
                            blockPos3,
                            blockState.with(KelpBlock.AGE, random.nextInt(4) + 20) as BlockState,
                            2
                        )
                        ++i
                    }
                    break
                }

                blockPos2 = blockPos2.up()
            }
        }

        return i > 0
    }
}