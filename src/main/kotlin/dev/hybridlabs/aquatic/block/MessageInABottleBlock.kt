@file:Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.Waterloggable
import net.minecraft.block.entity.BlockEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.WorldAccess

/**
 * Represents the Message in a Bottle block.
 * @see MessageInABottleBlockEntity
 */
class MessageInABottleBlock(settings: Settings) : BlockWithEntity(settings), Waterloggable {
    init {
        defaultState = defaultState.with(WATERLOGGED, false)
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        val world = context.world
        val pos = context.blockPos
        val fluidState = world.getFluidState(pos)
        return super.getPlacementState(context)?.with(WATERLOGGED, fluidState.fluid == Fluids.WATER)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        // append waterlogged
        super.appendProperties(
            builder.add(WATERLOGGED)
        )
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return MessageInABottleBlockEntity(pos, state)
    }
}
