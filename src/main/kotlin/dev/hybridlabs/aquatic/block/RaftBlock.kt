package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class RaftBlock(settings: Settings) : Block(settings), Waterloggable {
    init {
        defaultState = defaultState
            .with(Properties.WATERLOGGED, false)
    }
    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val waterlogged = ctx.world.getFluidState(ctx.blockPos).fluid == Fluids.WATER
        return defaultState
            .with(Properties.WATERLOGGED, waterlogged)
            .with(BuoyBlock.FACING, ctx.horizontalPlayerFacing.rotateYClockwise())
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
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
        builder.add(Properties.WATERLOGGED, FACING)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return if (state.get(Properties.WATERLOGGED)) SHAPE else LAND_SHAPE
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.up())
        if (fluidStateAbove.fluid != Fluids.EMPTY) {
            return false
        }
        val stateBelow = world.getBlockState(pos.down())
        if (stateBelow.block == this) {
            return false
        }
        val fluidState = world.getFluidState(pos)
        return fluidState.fluid == Fluids.WATER || sideCoversSmallSquare(world, pos.down(), Direction.UP)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING
        private val SHAPE: VoxelShape = createCuboidShape(1.0, 12.0, 1.0, 15.0, 16.0, 15.0)
        private val LAND_SHAPE: VoxelShape = createCuboidShape(1.0, 0.0, 1.0, 15.0, 2.5, 15.0)
    }
}