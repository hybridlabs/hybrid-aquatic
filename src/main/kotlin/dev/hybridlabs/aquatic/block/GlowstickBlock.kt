package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes.GLOW
import net.minecraft.particle.ParticleTypes.SMOKE
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

class GlowstickBlock(settings: Settings) : TorchBlock(settings, GLOW), Waterloggable {
    init {
        defaultState = stateManager.defaultState.with(Properties.WATERLOGGED, false)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        return if (!canPlaceAt(state, world, pos)) {
            Blocks.AIR.defaultState
        } else super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER)) defaultState.with(
            Properties.WATERLOGGED, ctx.world.getFluidState(ctx.blockPos).isOf(
                Fluids.WATER)) else null
    }

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return BOUNDING_SHAPE
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.WATERLOGGED)
    }
}