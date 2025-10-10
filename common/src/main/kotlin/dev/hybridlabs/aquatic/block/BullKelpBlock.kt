package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION")
class BullKelpBlock(settings: Properties) :
    GrowingPlantHeadBlock(settings, Direction.UP, SHAPE, true, 0.14),
    LiquidBlockContainer {
    override fun canGrowInto(state: BlockState): Boolean {
        return state.`is`(Blocks.WATER)
    }

    override fun getBodyBlock(): Block {
        return HybridAquaticBlocks.BULL_KELP_PLANT.get()
    }

    override fun canAttachTo(state: BlockState): Boolean {
        return !state.`is`(Blocks.MAGMA_BLOCK)
    }

    override fun canPlaceLiquid(
        p0: Player?,
        p1: BlockGetter,
        p2: BlockPos,
        p3: BlockState,
        p4: Fluid
    ): Boolean {
        return false
    }

    override fun placeLiquid(
        p0: LevelAccessor,
        p1: BlockPos,
        p2: BlockState,
        p3: FluidState
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

    override fun codec(): MapCodec<out GrowingPlantHeadBlock> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<BullKelpBlock> = simpleCodec(::BullKelpBlock)
        private val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
    }
}