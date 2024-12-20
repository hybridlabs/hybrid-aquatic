@file:Suppress("OVERRIDE_DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
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
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

@Suppress("DEPRECATION")
open class BuoyBlock(settings: Settings): Block(settings), BlockEntityProvider, Waterloggable {
    init {
        defaultState = stateManager.defaultState
            .with(Properties.WATERLOGGED, false)
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return BuoyBlockEntity(pos, state)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val waterlogged = ctx.world.getFluidState(ctx.blockPos).fluid == Fluids.WATER
        return defaultState
            .with(Properties.WATERLOGGED, waterlogged)
            .with(FACING, ctx.horizontalPlayerFacing.rotateYClockwise())
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
        return SHAPE
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape = COLLISION_SHAPE

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val placedOn = world.getBlockState(pos)
        val isAirAbove = world.getBlockState(pos.up()).isAir && world.getBlockState(pos.up(2)).isAir

        return placedOn.fluidState.isOf(Fluids.WATER) && isAirAbove
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING

        private val CUBE_SHAPE: VoxelShape = createCuboidShape(0.5, 3.0, 0.5, 15.5, 16.0, 15.5)
        private val POLE_SHAPE: VoxelShape = createCuboidShape(4.0, 0.0, 3.0, 12.0, 2.0, 13.0)

        private val SHAPE: VoxelShape = VoxelShapes.union(CUBE_SHAPE, POLE_SHAPE)
        private val COLLISION_SHAPE: VoxelShape = VoxelShapes.union(CUBE_SHAPE, POLE_SHAPE)
    }
}
