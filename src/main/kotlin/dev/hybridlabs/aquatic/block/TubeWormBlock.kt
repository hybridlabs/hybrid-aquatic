package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import net.minecraft.block.*
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.intprovider.IntProvider
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import org.jetbrains.annotations.Nullable

@Suppress("DEPRECATION", "SameParameterValue", "OVERRIDE_DEPRECATION")
class TubeWormBlock(settings: Settings) : PlantBlock(settings), Fertilizable, Waterloggable {
    companion object {
        val WORMS: IntProperty = IntProperty.of("worms", 1, 4)
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED

        val WORM_COUNT_CODEC: Codec<IntProvider> = IntProvider.createValidatingCodec(WORMS.min, WORMS.max)

        private val ONE_WORM_SHAPE: VoxelShape = createCuboidShape(6.0, 0.0, 6.0, 10.0, 8.0, 10.0)
        private val TWO_WORMS_SHAPE: VoxelShape = createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
        private val THREE_WORMS_SHAPE: VoxelShape = createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
        private val FOUR_WORMS_SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
    }

    init {
        defaultState = stateManager.defaultState.with(WORMS, WORMS.min).with(WATERLOGGED, true)
    }

    @Nullable
    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockState = ctx.world.getBlockState(ctx.blockPos)
        return if (blockState.isOf(this)) {
            blockState.with(WORMS, (blockState[WORMS] + 1).coerceAtMost(WORMS.max))
        } else {
            val fluidState = ctx.world.getFluidState(ctx.blockPos)
            val isWaterlogged = fluidState.fluid == Fluids.WATER
            super.getPlacementState(ctx)?.with(WATERLOGGED, isWaterlogged)
        }
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val blockPos = pos.down()
        return canPlantOnTop(world.getBlockState(blockPos), world, blockPos)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (!state.canPlaceAt(world, pos)) {
            Blocks.AIR.defaultState
        } else {
            if (state[WATERLOGGED]) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
            }
            super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
        }
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        return !context.shouldCancelInteraction() &&
                context.stack.isOf(asItem()) &&
                state[WORMS] < WORMS.max || super.canReplace(state, context)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
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
        return if (state[WATERLOGGED]) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WORMS, WATERLOGGED)
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
        return false
    }

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean {
        return false
    }

    override fun grow(world: ServerWorld, random: Random, pos: BlockPos, state: BlockState) {
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty ||
                floor.isSideSolidFullSquare(world, pos, Direction.UP)
    }
}
