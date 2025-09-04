@file:Suppress("OVERRIDE_DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

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

        return placedOn.fluidState.`is`(Fluids.WATER) && isAirAbove
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING

        private val CUBE_SHAPE: VoxelShape = box(0.5, 3.0, 0.5, 15.5, 16.0, 15.5)
        private val POLE_SHAPE: VoxelShape = box(6.0, 16.0, 6.0, 10.0, 42.0, 10.0)

        private val SHAPE: VoxelShape = Shapes.union(CUBE_SHAPE, POLE_SHAPE)
        private val COLLISION_SHAPE: VoxelShape = Shapes.union(CUBE_SHAPE, POLE_SHAPE)
    }
}
