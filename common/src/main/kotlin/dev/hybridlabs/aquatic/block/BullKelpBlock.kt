package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.WorldAccess

@Suppress("OVERRIDE_DEPRECATION")
class BullKelpBlock(settings: Properties?) :
    GrowingPlantHeadBlock(settings, Direction.UP, SHAPE, true, 0.14),
    LiquidBlockContainer {
    override fun canGrowInto(state: BlockState): Boolean {
        return state.`is`(Blocks.WATER)
    }

    override fun getBodyBlock(): Block {
        return HybridAquaticBlocks.BULL_KELP_PLANT
    }

    override fun canAttachTo(state: BlockState): Boolean {
        return !state.`is`(Blocks.MAGMA_BLOCK)
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

    override fun getBlocksToGrowWhenBonemealed(random: RandomSource): Int {
        return 1
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        return if (fluidState.`is`(FluidTags.WATER) && fluidState.amount == 8) super.getStateForPlacement(ctx) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getSource(false)
    }

    companion object {
        private val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
    }
}