package dev.hybridlabs.aquatic.block

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.BonemealableBlock
import net.minecraft.block.LiquidBlockContainer
import net.minecraft.block.PlantBlock
import net.minecraft.block.CollisionContext
import net.minecraft.block.TallSeagrassBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.registry.tag.FluidTags
import net.minecraft.server.world.ServerLevel
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader

@Suppress("OVERRIDE_DEPRECATION")
class RedAlgaeBlock(settings: Properties?) : BushBlock(settings), BonemealableBlock, LiquidBlockContainer {
    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext?
    ): VoxelShape {
        return SHAPE
    }

    override fun mayPlantOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return floor.isFaceSturdy(world, pos, Direction.UP) && !floor.isOf(Blocks.MAGMA_BLOCK)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        return if (fluidState.`is`(FluidTags.WATER) && fluidState.amount == 8) super.getStateForPlacement(ctx) else null
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        val blockState = super.updateShape(state, direction, neighborState, world, pos, neighborPos)
        if (!blockState.isAir) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return blockState
    }

    override fun isBonemealableBlock(world: LevelReader, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
        return true
    }

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getSource(false)
    }

    override fun.performBonemeal(world: ServerLevel, random: Random, pos: BlockPos, state: BlockState) {
        val blockState = HybridAquaticBlocks.TALL_RED_ALGAE.defaultBlockState()
        val blockState2 = blockState.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER) as BlockState
        val blockPos = pos.above()
        if (world.getBlockState(blockPos).isOf(Blocks.WATER)) {
            world.setBlockState(pos, blockState, 2)
            world.setBlockState(blockPos, blockState2, 2)
        }
    }

    override fun canPlaceLiquid(world: BlockGetter, pos: BlockPos, state: BlockState, fluid: Fluid): Boolean {
        return false
    }

    override fun placeLiquid(
        world: LevelAccessor,
        pos: BlockPos,
        state: BlockState,
        fluidState: FluidState
    ): Boolean {
        return false
    }

    companion object {
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)
    }
}
