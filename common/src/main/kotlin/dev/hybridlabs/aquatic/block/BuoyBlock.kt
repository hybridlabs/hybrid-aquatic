@file:Suppress("OVERRIDE_DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.CollisionContext
import net.minecraft.block.SimpleWaterloggedBlcok
import net.minecraft.block.entity.BlockEntity
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
import net.minecraft.util.shape.Shapes
import net.minecraft.world.BlockGetter
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader

@Suppress("DEPRECATION")
open class BuoyBlock(settings: Properties): Block(settings), BlockEntityProvider, SimpleWaterloggedBlcok {
    init {
        defaultBlockState() = stateManager.defaultBlockState()
            .with(Properties.WATERLOGGED, false)
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return BuoyBlockEntity(pos, state)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos).fluid == Fluids.WATER
        return defaultBlockState()
            .with(Properties.WATERLOGGED, waterlogged)
            .with(FACING, ctx.horizontalPlayerFacing.rotateYClockwise())
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

        return placedOn.fluidState.isOf(Fluids.WATER) && isAirAbove
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING

        private val CUBE_SHAPE: VoxelShape = box(0.5, 3.0, 0.5, 15.5, 16.0, 15.5)
        private val POLE_SHAPE: VoxelShape = box(6.0, 16.0, 6.0, 10.0, 42.0, 10.0)

        private val SHAPE: VoxelShape = Shapes.union(CUBE_SHAPE, POLE_SHAPE)
        private val COLLISION_SHAPE: VoxelShape = Shapes.union(CUBE_SHAPE, POLE_SHAPE)
    }
}
