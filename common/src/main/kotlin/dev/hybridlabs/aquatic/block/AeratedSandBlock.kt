package dev.hybridlabs.aquatic.block

import net.minecraft.world.level.LevelReader

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.ColorRGBA
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ColoredFallingBlock
import net.minecraft.world.level.block.state.BlockState

@Suppress("OVERRIDE_DEPRECATION")
class AeratedSandBlock(dustColor: ColorRGBA, settings: Properties) : ColoredFallingBlock(dustColor, settings) {
    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        DecorativeBubbleColumnBlock.updateColumn(level, pos.above(), state)
    }

    override fun updateShape(
        state: BlockState,
        level: LevelReader,
        scheduledTickAccess: ScheduledTickAccess,
        currentPos: BlockPos,
        facing: Direction,
        facingPos: BlockPos,
        facingState: BlockState,
        random: RandomSource
    ): BlockState {
        if (facing == Direction.UP && facingState.`is`(Blocks.WATER)) {
            scheduledTickAccess.scheduleTick(currentPos, this, DECORATIVE_BUBBLE_COLUMN_CHECK_DELAY)
        }

        return super.updateShape(state, level, scheduledTickAccess, currentPos, facing, facingPos, facingState, random)
    }

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, isMoving: Boolean) {
        level.scheduleTick(pos, this, DECORATIVE_BUBBLE_COLUMN_CHECK_DELAY)
    }

    companion object {
        const val DECORATIVE_BUBBLE_COLUMN_CHECK_DELAY = 20
    }
}