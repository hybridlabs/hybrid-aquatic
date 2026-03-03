package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.LiquidBlockContainer
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class ClamBlock(properties: Properties) : CropBlock(properties),
    LiquidBlockContainer {

    override fun getBaseSeedId(): ItemLike {
        return HybridAquaticItems.CLAM.get()
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return SHAPE_BY_AGE[this.getAge(state)]
    }

    override fun mayPlaceOn(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean {
        return state.`is`(Blocks.SAND)
    }

    override fun canPlaceLiquid(world: BlockGetter, pos: BlockPos, state: BlockState, fluid: Fluid): Boolean {
        return false
    }

    override fun placeLiquid(
        world: LevelAccessor,
        pos: BlockPos,
        state: BlockState,
        fluidState: FluidState,
    ): Boolean {
        return false
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        return if (fluidState.`is`(FluidTags.WATER) && fluidState.amount == 8) super.getStateForPlacement(ctx) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getSource(false)
    }

    companion object {
        private val SHAPE_BY_AGE = arrayOf<VoxelShape>(
            box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
        )
    }
}