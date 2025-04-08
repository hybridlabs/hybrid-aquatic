package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.registry.tag.FluidTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

@Suppress("OVERRIDE_DEPRECATION")
class SeaLettuceBlock(settings: Settings) : PlantBlock(settings), Fertilizable, FluidFillable {
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return floor.isSideSolidFullSquare(world, pos, Direction.UP) && !floor.isOf(Blocks.MAGMA_BLOCK)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER) && fluidState.level == 8) super.getPlacementState(ctx) else null
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        val blockState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
        if (!blockState.isAir) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        return blockState
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
        return true
    }

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getStill(false)
    }

    override fun grow(world: ServerWorld, random: Random, pos: BlockPos, state: BlockState) {
        val blockState = HybridAquaticBlocks.TALL_SEA_LETTUCE.defaultState
        val blockState2 = blockState.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER) as BlockState
        val blockPos = pos.up()
        if (world.getBlockState(blockPos).isOf(Blocks.WATER)) {
            world.setBlockState(pos, blockState, 2)
            world.setBlockState(blockPos, blockState2, 2)
        }
    }

    override fun canFillWithFluid(world: BlockView, pos: BlockPos, state: BlockState, fluid: Fluid): Boolean {
        return false
    }

    override fun tryFillWithFluid(
        world: WorldAccess,
        pos: BlockPos,
        state: BlockState,
        fluidState: FluidState
    ): Boolean {
        return false
    }

    companion object {
        private val SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)
    }
}