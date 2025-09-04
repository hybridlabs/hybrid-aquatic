package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.Shapes
import net.minecraft.world.BlockGetter
import net.minecraft.world.WorldAccess

@Suppress("OVERRIDE_DEPRECATION")
class BullKelpPlantBlock(settings: Properties) :
    GrowingPlantBodyBlock(settings, Direction.UP, Shapes.block(), true), LiquidBlockContainer {
    override fun getHeadBlock(): GrowingPlantHeadBlock {
        return HybridAquaticBlocks.BULL_KELP as GrowingPlantHeadBlock
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getSource(false)
    }

    override fun canAttachTo(state: BlockState): Boolean {
        return state.`is`(Blocks.SAND) || state.`is`(Blocks.GRAVEL) || state.`is`(headBlock) || super.canAttachTo(state)
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
}