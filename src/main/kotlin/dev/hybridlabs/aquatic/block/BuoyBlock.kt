@file:Suppress("OVERRIDE_DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

open class BuoyBlock(settings: Settings): Block(settings), BlockEntityProvider, Waterloggable {
    init {
        defaultState = stateManager.defaultState.with(Properties.WATERLOGGED, false)
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return BuoyBlockEntity(pos, state)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER)) defaultState.with(
            Properties.WATERLOGGED, ctx.world.getFluidState(ctx.blockPos).isOf(
                Fluids.WATER)) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (canPlaceAt(state, world, pos)) super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
        else Blocks.AIR.defaultState
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.WATERLOGGED)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val placedOn = world.getBlockState(pos)
        val isAirAbove = world.getBlockState(pos.up()).isAir && world.getBlockState(pos.up(2)).isAir

        return placedOn.fluidState.isOf(Fluids.WATER) && isAirAbove
    }

    companion object {
        val SHAPE: VoxelShape = createCuboidShape(1.0, 3.0, 1.0, 15.0, 16.0, 15.0)
    }
}
