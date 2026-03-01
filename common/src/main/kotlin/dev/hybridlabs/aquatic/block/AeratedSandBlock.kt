package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SandBlock
import net.minecraft.world.level.block.state.BlockState

@Suppress("OVERRIDE_DEPRECATION")
class AeratedSandBlock(dustColor: Int, settings: Properties) : SandBlock(dustColor, settings) {
    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        DecorativeBubbleColumnBlock.updateColumn(level, pos.above(), state)
    }

    override fun updateShape(
        state: BlockState,
        facing: Direction,
        facingState: BlockState,
        level: LevelAccessor,
        currentPos: BlockPos,
        facingPos: BlockPos,
    ): BlockState {
        if (facing == Direction.UP && facingState.`is`(Blocks.WATER)) {
            level.scheduleTick(currentPos, this, DECORATIVE_BUBBLE_COLUMN_CHECK_DELAY)
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
    }

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, isMoving: Boolean) {
        level.scheduleTick(pos, this, DECORATIVE_BUBBLE_COLUMN_CHECK_DELAY)
    }

    companion object {
        const val DECORATIVE_BUBBLE_COLUMN_CHECK_DELAY = 20
    }
}