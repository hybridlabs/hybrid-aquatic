package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.FluidFillable
import net.minecraft.block.ShapeContext
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.property.EnumProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

class TallSeaLettuceBlock(settings: Settings?) : TallPlantBlock(settings), FluidFillable {
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

    override fun getPickStack(world: WorldView, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HybridAquaticBlocks.SEA_LETTUCE)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockState = super.getPlacementState(ctx)
        if (blockState != null) {
            val fluidState = ctx.world.getFluidState(ctx.blockPos.up())
            if (fluidState.isIn(FluidTags.WATER) && fluidState.level == 8) {
                return blockState
            }
        }

        return null
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            val blockState = world.getBlockState(pos.down())
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER
        } else {
            val fluidState = world.getFluidState(pos)
            return super.canPlaceAt(state, world, pos) && fluidState.isIn(FluidTags.WATER) && fluidState.level == 8
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getStill(false)
    }

    override fun canFillWithFluid(player: PlayerEntity?, world: BlockView, pos: BlockPos, state: BlockState, fluid: Fluid): Boolean {
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

    override fun getCodec(): MapCodec<TallSeaLettuceBlock> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<TallSeaLettuceBlock> = createCodec(::TallSeaLettuceBlock)
        val HALF: EnumProperty<DoubleBlockHalf> = TallPlantBlock.HALF
        private val SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
    }
}
