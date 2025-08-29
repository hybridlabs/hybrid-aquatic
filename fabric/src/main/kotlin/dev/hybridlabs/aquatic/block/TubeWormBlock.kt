package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.jetbrains.annotations.Nullable

@Suppress("DEPRECATION", "SameParameterValue", "OVERRIDE_DEPRECATION")
class TubeWormBlock(settings: Properties) : BushBlock(settings), BonemealableBlock, SimpleWaterloggedBlock {
    companion object {
        val WORMS: IntegerProperty = IntegerProperty.create("worms", 1, 4)
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        val WORM_COUNT_CODEC: Codec<IntProvider> = IntProvider.codec(1, 4)

        private val ONE_WORM_SHAPE: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0)
        private val TWO_WORMS_SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
        private val THREE_WORMS_SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
        private val FOUR_WORMS_SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
    }

    init {
        this.registerDefaultState(stateDefinition.any().setValue(WORMS, WORMS.min).setValue(WATERLOGGED, true))
    }

    @Nullable
    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val blockState = ctx.level.getBlockState(ctx.clickedPos)
        return if (blockState.`is`(this)) {
            blockState.setValue(WORMS, (blockState.getValue(WORMS) + 1).coerceAtMost(WORMS.max))
        } else {
            val fluidState = ctx.level.getFluidState(ctx.clickedPos)
            val isWaterlogged = fluidState.`is`(Fluids.WATER)
            super.getStateForPlacement(ctx)?.setValue(WATERLOGGED, isWaterlogged)
        }
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val blockPos = pos.below()
        return mayPlaceOn(world.getBlockState(blockPos), world, blockPos)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (!state.canSurvive(world, pos)) {
            Blocks.AIR.defaultBlockState()
        } else {
            if (state.getValue(WATERLOGGED)) {
                world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
            }
            super.updateShape(state, direction, neighborState, world, pos, neighborPos)
        }
    }

    override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean {
        return !context.isSecondaryUseActive() &&
                context.itemInHand.`is`(asItem()) &&
                state.getValue(WORMS) < WORMS.max || super.canBeReplaced(state, context)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return when (state.getValue(WORMS)) {
            1 -> ONE_WORM_SHAPE
            2 -> TWO_WORMS_SHAPE
            3 -> THREE_WORMS_SHAPE
            4 -> FOUR_WORMS_SHAPE
            else -> ONE_WORM_SHAPE
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(WORMS, WATERLOGGED)
    }

    override fun isValidBonemealTarget(
        world: LevelReader,
        pos: BlockPos,
        state: BlockState,
        isClient: Boolean
    ): Boolean {
        return false
    }

    override fun isBonemealSuccess(world: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean {
        return false
    }

    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
    }

    override fun isPathfindable(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        type: PathComputationType
    ): Boolean {
        return false
    }

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return !floor.getCollisionShape(world, pos).getFaceShape(Direction.UP).isEmpty ||
                floor.isFaceSturdy(world, pos, Direction.UP)
    }
}
