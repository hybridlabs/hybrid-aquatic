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

class SargassumFeature(codec: Codec<SargassumFeatureConfig>) :
    Feature<SargassumFeatureConfig>(codec) {
    override fun generate(context: FeatureContext<SargassumFeatureConfig>): Boolean {
        var i = 0
        val structureWorldAccess = context.world
        val blockPos = context.origin
        val random = context.random
        val j = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, blockPos.x, blockPos.z)
        var blockPos2 = BlockPos(blockPos.x, j, blockPos.z)
        if (structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER)) {
            val blockState = HybridAquaticBlocks.SARGASSUM.defaultState
            val blockState2 = HybridAquaticBlocks.SARGASSUM_PLANT.defaultState
            val k = 1 + random.nextInt(10)

            for (l in 0..k) {
                if (structureWorldAccess.getBlockState(blockPos2)
                        .isOf(Blocks.WATER) && structureWorldAccess.getBlockState(blockPos2.up())
                        .isOf(Blocks.WATER) && blockState2.canPlaceAt(structureWorldAccess, blockPos2)
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
                    if (blockState.canPlaceAt(structureWorldAccess, blockPos3) && !structureWorldAccess.getBlockState(
                            blockPos3.down()
                        ).isOf(HybridAquaticBlocks.SARGASSUM)
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