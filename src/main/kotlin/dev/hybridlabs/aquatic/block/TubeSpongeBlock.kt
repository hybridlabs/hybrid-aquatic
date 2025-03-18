package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class TubeSpongeBlock(
    private val emitsParticles: Boolean,
    settings: Settings
) : PlantBlock(settings), Waterloggable {

    private var bubbleTimer = 0

    init {
        defaultState = stateManager.defaultState
            .with(WATERLOGGED, true)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val supportingPos = pos.down()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.isSideSolidFullSquare(world, supportingPos, Direction.UP)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        return if (!canPlaceAt(state, world, pos)) {
            Blocks.AIR.defaultState
        } else super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape {
        return SHAPE
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER)) defaultState.with(
            WATERLOGGED, ctx.world.getFluidState(ctx.blockPos).isOf(
                Fluids.WATER)) else null
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        if (state.get(WATERLOGGED) && emitsParticles && bubbleTimer % 20 == 0) {
            (bubbleTimer / 60).toFloat() * 0.05f
            val upwardVelocity = 0.1f

            world.addParticle(
                ParticleTypes.BUBBLE_COLUMN_UP,
                pos.x + 0.5, pos.y + 0.75, pos.z + 0.5,
                random.nextFloat() / 2.0, upwardVelocity.toDouble(), random.nextFloat() / 2.0
            )

            bubbleTimer = 0
        }

        bubbleTimer++
    }

    companion object {
        private val SHAPE = createCuboidShape(4.0, 0.0, 4.0, 12.0, 12.0, 12.0)
        private val COLLISION_SHAPE = createCuboidShape(4.0, 0.0, 4.0, 12.0, 12.0, 12.0)
    }
}