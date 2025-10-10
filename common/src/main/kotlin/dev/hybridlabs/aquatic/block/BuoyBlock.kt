@file:Suppress("OVERRIDE_DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("DEPRECATION")
open class BuoyBlock(settings: Properties): Block(settings), EntityBlock, SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false))
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return false
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return BuoyBlockEntity(pos, state)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos) == Fluids.WATER.getSource(false)
        return defaultBlockState()
            .setValue(WATERLOGGED, waterlogged)
            .setValue(FACING, ctx.horizontalDirection.clockWise)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (canSurvive(state, world, pos)) super.updateShape(state, direction, neighborState, world, pos, neighborPos)
        else Blocks.AIR.defaultBlockState()
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED, FACING)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape = COLLISION_SHAPE

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val placedOn = world.getBlockState(pos)
        val isAirAbove = world.getBlockState(pos.above()).isAir && world.getBlockState(pos.above(2)).isAir

        return placedOn.fluidState.`is`(Fluids.WATER) && isAirAbove
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING

        private val CUBE_SHAPE: VoxelShape = box(0.5, 3.0, 0.5, 15.5, 16.0, 15.5)
        private val POLE_SHAPE: VoxelShape = box(6.0, 16.0, 6.0, 10.0, 42.0, 10.0)

        private val SHAPE: VoxelShape = Shapes.or(CUBE_SHAPE, POLE_SHAPE)
        private val COLLISION_SHAPE: VoxelShape = Shapes.or(CUBE_SHAPE, POLE_SHAPE)
    }
}
