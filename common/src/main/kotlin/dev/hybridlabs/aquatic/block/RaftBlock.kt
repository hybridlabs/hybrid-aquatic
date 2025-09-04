package dev.hybridlabs.aquatic.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.CollisionContext
import net.minecraft.block.SimpleWaterloggedBlcok
import net.minecraft.entity.ai.pathing.PathComputationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class RaftBlock(settings: Properties) : Block(settings), SimpleWaterloggedBlcok {
    init {
        defaultBlockState() = defaultBlockState()
            .with(Properties.WATERLOGGED, false)
    }
    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos).fluid == Fluids.WATER
        return defaultBlockState()
            .with(Properties.WATERLOGGED, waterlogged)
            .with(BuoyBlock.FACING, ctx.horizontalPlayerFacing.rotateYClockwise())
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
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

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.WATERLOGGED, FACING)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext?
    ): VoxelShape {
        return if (state.get(Properties.WATERLOGGED)) SHAPE else LAND_SHAPE
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.above())
        if (fluidStateAbove.fluid != Fluids.EMPTY) {
            return false
        }
        val stateBelow = world.getBlockState(pos.down())
        if (stateBelow.block == this) {
            return false
        }
        val fluidState = world.getFluidState(pos)
        return fluidState.fluid == Fluids.WATER || sideCoversSmallSquare(world, pos.below(), Direction.UP)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING
        private val SHAPE: VoxelShape = box(1.0, 12.0, 1.0, 15.0, 16.0, 15.0)
        private val LAND_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 2.5, 15.0)
    }
}
