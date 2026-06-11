package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class RaftBlock(settings: Properties) : Block(settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, true))
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos) == Fluids.WATER.getSource(false)
        return defaultBlockState()
            .setValue(WATERLOGGED, waterlogged)
            .setValue(FACING, ctx.horizontalDirection.clockWise)
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return false
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
        return if (state.getValue(WATERLOGGED)) SHAPE else LAND_SHAPE
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.above())
        if (!fluidStateAbove.`is`( Fluids.EMPTY)) {
            return false
        }

        val stateBelow = world.getBlockState(pos.below())
        if (stateBelow.block == this) {
            return false
        }

        val fluidState = world.getFluidState(pos)
        return fluidState.`is`(Fluids.WATER) || canSupportCenter(world, pos.below(), Direction.UP)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING
        private val SHAPE: VoxelShape = box(1.0, 12.0, 1.0, 15.0, 16.0, 15.0)
        private val LAND_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 2.5, 15.0)
    }
}