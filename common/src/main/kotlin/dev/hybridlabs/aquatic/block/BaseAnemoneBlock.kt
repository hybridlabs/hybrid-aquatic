package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
open class BaseAnemoneBlock(settings: Properties) : DirectionalBlock(settings),
    EntityBlock, SimpleWaterloggedBlock {

    init {
        this.registerDefaultState(
            this.stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(WATERLOGGED, true)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING, WATERLOGGED)
        super.createBlockStateDefinition(builder)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val fluidState = context.level.getFluidState(context.clickedPos)
        val baseState = this.defaultBlockState().setValue(FACING, context.clickedFace)

        return if (fluidState.`is`(FluidTags.WATER)) {
            baseState.setValue(WATERLOGGED, true)
        } else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if(state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }

    override fun isPathfindable(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        type: PathComputationType,
    ): Boolean {
        return false
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        level: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))

        return if (!canSurvive(state, level, pos)) {
            Blocks.AIR.defaultBlockState()
        } else super.updateShape(state, direction, neighborState, level, pos, neighborPos)
    }

    override fun getCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return when(state.getValue(FACING) as Direction) {
            Direction.DOWN -> FLOOR_SHAPE
            Direction.UP -> CEILING_SHAPE
            Direction.NORTH -> NORTH_SHAPE
            Direction.SOUTH -> SOUTH_SHAPE
            Direction.WEST -> WEST_SHAPE
            Direction.EAST -> EAST_SHAPE
        }
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when(state.getValue(FACING) as Direction) {
            Direction.DOWN -> FLOOR_SHAPE
            Direction.UP -> CEILING_SHAPE
            Direction.NORTH -> NORTH_SHAPE
            Direction.SOUTH -> SOUTH_SHAPE
            Direction.WEST -> WEST_SHAPE
            Direction.EAST -> EAST_SHAPE
        }
    }

    override fun newBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ): BlockEntity {
        TODO("Not yet implemented")
    }

    companion object {
        private val CEILING_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
        private val FLOOR_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
        private val NORTH_SHAPE: VoxelShape = box(1.0, 1.0, 0.0, 15.0, 15.0, 16.0)
        private val SOUTH_SHAPE: VoxelShape = box(1.0, 1.0, 0.0, 15.0, 15.0, 16.0)
        private val WEST_SHAPE: VoxelShape = box(0.0, 1.0, 1.0, 16.0, 15.0, 15.0)
        private val EAST_SHAPE: VoxelShape = box(0.0, 1.0, 1.0, 16.0, 15.0, 15.0)
    }
}
