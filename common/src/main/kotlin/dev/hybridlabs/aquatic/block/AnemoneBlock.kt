package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
class AnemoneBlock(settings: Properties) : FaceAttachedHorizontalDirectionalBlock(settings), EntityBlock,
    SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(WATERLOGGED, true)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(FACING, FACE, WATERLOGGED)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return if (!canSurvive(state, world, pos)) {
            Blocks.AIR.defaultBlockState()
        }
        else super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        val direction = state.getValue(FACING)
        when (state.getValue(FACE) as AttachFace) {
            AttachFace.FLOOR -> {
                return FLOOR_SHAPE
            }

            AttachFace.WALL -> {
                val voxelShape: VoxelShape = when (direction) {
                    Direction.EAST -> EAST_SHAPE
                    Direction.WEST -> WEST_SHAPE
                    Direction.SOUTH -> SOUTH_SHAPE
                    Direction.NORTH, Direction.UP, Direction.DOWN -> NORTH_SHAPE

                    else -> throw IncompatibleClassChangeError()
                }

                return voxelShape
            }

            AttachFace.CEILING -> {
                return CEILING_SHAPE
            }
        }
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        val baseState = super.getStateForPlacement(ctx) ?: return null

        return if (fluidState.`is`(FluidTags.WATER)) {
            baseState.setValue(WATERLOGGED, true)
        } else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.hasProperty(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return AnemoneBlockEntity(pos, state)
    }

    override fun isPathfindable(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        type: PathComputationType,
    ): Boolean {
        return false
    }

    companion object {
        private val CEILING_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 16.0, 16.0)
        private val FLOOR_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
        private val NORTH_SHAPE: VoxelShape = box(1.0, 0.0, 0.0, 15.0, 16.0, 15.0)
        private val SOUTH_SHAPE: VoxelShape = box(1.0, 0.0, 0.0, 15.0, 16.0, 15.0)
        private val WEST_SHAPE: VoxelShape = box(0.0, 0.0, 1.0, 15.0, 16.0, 15.0)
        private val EAST_SHAPE: VoxelShape = box(0.0, 0.0, 1.0, 15.0, 16.0, 15.0)
        private val COLLISION_SHAPE = box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    }
}