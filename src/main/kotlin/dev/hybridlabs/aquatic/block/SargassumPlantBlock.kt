package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

class SargassumPlantBlock(settings: Settings) :
    AbstractPlantBlock(settings, Direction.UP, VoxelShapes.fullCube(), true), FluidFillable {
    override fun getStem(): AbstractPlantStemBlock {
        return HybridAquaticBlocks.SARGASSUM as AbstractPlantStemBlock
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getStill(false)
    }

    override fun canAttachTo(state: BlockState): Boolean {
        return state.isOf(Blocks.SAND) || state.isOf(Blocks.GRAVEL) || state.isOf(stem) || super.canAttachTo(state)
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
}