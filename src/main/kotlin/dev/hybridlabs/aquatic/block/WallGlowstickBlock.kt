package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes.BUBBLE_COLUMN_UP
import net.minecraft.particle.ParticleTypes.GLOW
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

class WallGlowstickBlock(settings: Settings) : WallTorchBlock(GLOW, settings), Waterloggable {
    init {
        defaultState = stateManager.defaultState.with(Properties.WATERLOGGED, false)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        val fluidState = context.world.getFluidState(context.blockPos)
        return super.getPlacementState(context)?.with(Properties.WATERLOGGED, fluidState == Fluids.WATER.getStill(false))
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder.add(Properties.WATERLOGGED))
    }
}
