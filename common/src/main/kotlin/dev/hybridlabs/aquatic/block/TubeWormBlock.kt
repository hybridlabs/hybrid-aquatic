package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.BonemealableBlock
import net.minecraft.block.PlantBlock
import net.minecraft.block.CollisionContext
import net.minecraft.block.SimpleWaterloggedBlcok
import net.minecraft.entity.ai.pathing.PathComputationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.server.world.ServerLevel
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.intprovider.IntProvider
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader
import org.jetbrains.annotations.Nullable

@Suppress("DEPRECATION", "SameParameterValue", "OVERRIDE_DEPRECATION")
class TubeWormBlock(settings: Properties) : BushBlock(settings), BonemealableBlock, SimpleWaterloggedBlcok {
    companion object {
        val WORMS: IntProperty = IntProperty.of("worms", 1, 4)
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED

        val WORM_COUNT_CODEC: Codec<IntProvider> = IntProvider.createValidatingCodec(WORMS.min, WORMS.max)

        private val ONE_WORM_SHAPE: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0)
        private val TWO_WORMS_SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
        private val THREE_WORMS_SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
        private val FOUR_WORMS_SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
    }

    init {
        defaultBlockState() = stateManager.defaultBlockState().with(WORMS, WORMS.min).with(WATERLOGGED, true)
    }

    @Nullable
    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val blockState = ctx.level.getBlockState(ctx.clickedPos)
        return if (blockState.isOf(this)) {
            blockState.with(WORMS, (blockState[WORMS] + 1).coerceAtMost(WORMS.max))
        } else {
            val fluidState = ctx.level.getFluidState(ctx.clickedPos)
            val isWaterlogged = fluidState.fluid == Fluids.WATER
            super.getStateForPlacement(ctx)?.with(WATERLOGGED, isWaterlogged)
        }
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val blockPos = pos.below()
        return mayPlantOn(world.getBlockState(blockPos), world, blockPos)
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
            if (state[WATERLOGGED]) {
                world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
            }
            super.updateShape(state, direction, neighborState, world, pos, neighborPos)
        }
    }

    override fun canReplace(state: BlockState, context: BlockPlaceContext): Boolean {
        return !context.shouldCancelInteraction() &&
                context.stack.isOf(asItem()) &&
                state[WORMS] < WORMS.max || super.canReplace(state, context)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext?
    ): VoxelShape {
        return when (state[WORMS]) {
            1 -> ONE_WORM_SHAPE
            2 -> TWO_WORMS_SHAPE
            3 -> THREE_WORMS_SHAPE
            4 -> FOUR_WORMS_SHAPE
            else -> ONE_WORM_SHAPE
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state[WATERLOGGED]) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WORMS, WATERLOGGED)
    }

    override fun isBonemealableBlock(world: LevelReader, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
        return false
    }

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean {
        return false
    }

    override fun.performBonemeal(world: ServerLevel, random: Random, pos: BlockPos, state: BlockState) {
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun mayPlantOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty ||
                floor.isFaceSturdy(world, pos, Direction.UP)
    }
}
