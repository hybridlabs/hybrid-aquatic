package dev.hybridlabs.aquatic.block

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.LiquidBlockContainer
import net.minecraft.block.CollisionContext
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.property.EnumProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader

class TallSeaLettuceBlock(settings: Properties?) : TallPlantBlock(settings), LiquidBlockContainer {
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

    override fun getCloneItemStack(world: BlockGetter, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HybridAquaticBlocks.SEA_LETTUCE)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val blockState = super.getStateForPlacement(ctx)
        if (blockState != null) {
            val fluidState = ctx.level.getFluidState(ctx.blockPos.up())
            if (fluidState.`is`(FluidTags.WATER) && fluidState.amount == 8) {
                return blockState
            }
        }

        return null
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            val blockState = world.getBlockState(pos.down())
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER
        } else {
            val fluidState = world.getFluidState(pos)
            return super.canSurvive(state, world, pos) && fluidState.`is`(FluidTags.WATER) && fluidState.amount == 8
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getSource(false)
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
        val HALF: EnumProperty<DoubleBlockHalf> = TallPlantBlock.HALF
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
    }
}
