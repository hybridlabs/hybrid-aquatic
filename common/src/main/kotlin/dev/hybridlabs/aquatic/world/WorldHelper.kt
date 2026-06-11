package dev.hybridlabs.aquatic.world

import net.minecraft.core.BlockPos
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.state.BlockState

object WorldHelper {

    fun canSeeSkyFromBelowWater(serverLevel: ServerLevelAccessor, pos: BlockPos): Boolean {
        val seaLevel = serverLevel.level.chunkSource.generator.seaLevel

        if (pos.y >= seaLevel) {
            return serverLevel.canSeeSky(pos)
        } else {
            val blockPos = BlockPos(pos.x, seaLevel, pos.z)
            if (!serverLevel.canSeeSky(blockPos)) {
                return false
            } else {
                var blockPos1 = blockPos.below()
                while (blockPos1.y > pos.y) {
                    val blockState: BlockState = serverLevel.getBlockState(blockPos1)
                    if (blockState.getLightBlock(serverLevel, blockPos1) > 0 && !blockState.liquid()) {
                        return false
                    }
                    blockPos1 = blockPos1.below()
                }

                return true
            }
        }
    }
}