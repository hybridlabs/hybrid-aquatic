package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes.GLOW
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.WallTorchBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType

@Suppress("unused", "DEPRECATION")
class WallGlowstickBlock(settings: Properties) : WallTorchBlock(settings, GLOW), SimpleWaterloggedBlock {
    init {
        registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false))
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val fluidState = context.level.getFluidState(context.clickedPos)
        return super.getStateForPlacement(context)
            ?.setValue(WATERLOGGED, fluidState == Fluids.WATER.getSource(false))
    }

    @Deprecated("Deprecated in Java")
    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        super.createBlockStateDefinition(builder.add(WATERLOGGED))
    }

    @Deprecated("Deprecated in Java")
    override fun isPathfindable(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        type: PathComputationType
    ): Boolean {
        return true
    }
}