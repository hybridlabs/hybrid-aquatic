package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

@Suppress("OVERRIDE_DEPRECATION")
class SargassumBlock(settings: Settings?) :
    AbstractPlantStemBlock(settings, Direction.UP, SHAPE, true, 0.14),
    FluidFillable {
    override fun chooseStemState(state: BlockState): Boolean {
        return state.isOf(Blocks.WATER)
    }

    override fun getPlant(): Block {
        return HybridAquaticBlocks.SARGASSUM_PLANT
    }

    override fun canAttachTo(state: BlockState): Boolean {
        return !state.isOf(Blocks.MAGMA_BLOCK)
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

    override fun getGrowthLength(random: Random): Int {
        return 1
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER) && fluidState.level == 8) super.getPlacementState(ctx) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getStill(false)
    }

    companion object {
        protected val SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
    }
}