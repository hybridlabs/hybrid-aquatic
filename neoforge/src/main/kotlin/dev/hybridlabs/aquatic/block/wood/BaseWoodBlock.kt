package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState

open class BaseWoodBlock(settings: Properties) : RotatedPillarBlock(settings) {
    override fun isFlammable(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction): Int {
        return 5
    }

    override fun getFireSpreadSpeed(
        state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
    ): Int {
        return 5
    }

}