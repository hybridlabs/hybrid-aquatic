package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class WaterLettuceBlock(settings: Properties) : BushBlock(settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, true))
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.above())
        if (!fluidStateAbove.`is`(Fluids.EMPTY)) {
            return false
        }

        val stateBelow = world.getBlockState(pos.below())
        if (stateBelow.block == this) {
            return false
        }

        val fluidState = world.getFluidState(pos)
        return fluidState == Fluids.WATER || canSupportCenter(world, pos.below(), Direction.UP)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val world = context.level
        val pos = context.clickedPos
        val fluidState = world.getFluidState(pos)
        return if (fluidState.`is`(Fluids.WATER)) {
            super.getStateForPlacement(context)?.setValue(WATERLOGGED, true)
        } else {
            null
        }
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

        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(WATERLOGGED)
    }

    override fun entityInside(state: BlockState, world: Level, pos: BlockPos, entity: Entity) {
        super.entityInside(state, world, pos, entity)
        if (world is ServerLevel && entity is Boat) {
            entity.makeStuckInBlock(state, Vec3(0.66, 0.66, 0.66))
            world.destroyBlock(BlockPos(pos), true, entity)
        }
    }

    companion object {
        private val SHAPE: VoxelShape = box(3.0, 14.0, 3.0, 13.0, 16.0, 13.0)
    }
}