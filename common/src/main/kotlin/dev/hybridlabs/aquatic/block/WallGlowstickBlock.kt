package dev.hybridlabs.aquatic.block

import net.minecraft.world.level.LevelReader

import net.minecraft.util.RandomSource

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes.GLOW
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.WallTorchBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType

@Suppress("unused", "DEPRECATION")
class WallGlowstickBlock(settings: Properties) : WallTorchBlock(GLOW, settings), SimpleWaterloggedBlock {
    init {
        registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false))
    }

    override fun updateShape(
        state: BlockState,
        world: LevelReader,
        scheduledTickAccess: ScheduledTickAccess,
        pos: BlockPos,
        direction: Direction,
        neighborPos: BlockPos,
        neighborState: BlockState,
        random: RandomSource
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return super.updateShape(state, world, scheduledTickAccess, pos, direction, neighborPos, neighborState, random)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val fluidState = context.level.getFluidState(context.clickedPos)
        return super.getStateForPlacement(context)
            ?.setValue(WATERLOGGED, fluidState == Fluids.WATER.getSource(false))
    }

    @Deprecated("Deprecated in Java")
    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder.add(WATERLOGGED))
    }

    @Deprecated("Deprecated in Java")
    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return true
    }
}