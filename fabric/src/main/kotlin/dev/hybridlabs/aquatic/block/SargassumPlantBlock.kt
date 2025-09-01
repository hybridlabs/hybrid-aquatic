package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.GrowingPlantBodyBlock
import net.minecraft.world.level.block.GrowingPlantHeadBlock
import net.minecraft.world.level.block.LiquidBlockContainer
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.Shapes

class SargassumBushBlock(settings: Properties) :
    GrowingPlantBodyBlock(settings, Direction.UP, Shapes.block(), true), LiquidBlockContainer {
    override fun getHeadBlock(): GrowingPlantHeadBlock {
        return HybridAquaticBlocks.SARGASSUM.get() as GrowingPlantHeadBlock
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