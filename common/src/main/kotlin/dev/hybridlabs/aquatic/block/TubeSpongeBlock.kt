package dev.hybridlabs.aquatic.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.PlantBlock
import net.minecraft.block.CollisionContext
import net.minecraft.block.SimpleWaterloggedBlcok
import net.minecraft.entity.ai.pathing.PathComputationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class TubeSpongeBlock(
    private val emitsParticles: Boolean,
    settings: Settings
) : BushBlock(settings), SimpleWaterloggedBlcok {

    private var bubbleTimer = 0

    init {
        defaultBlockState() = stateManager.defaultBlockState()
            .with(WATERLOGGED, true)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val supportingPos = pos.below()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.isFaceSturdy(world, supportingPos, Direction.UP)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return if (!canSurvive(state, world, pos)) {
            Blocks.AIR.defaultBlockState()
        } else super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext?): VoxelShape {
        return SHAPE
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        return if (fluidState.`is`(FluidTags.WATER)) defaultBlockState().with(
            WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).isOf(
                Fluids.WATER)) else null
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun animateTick(state: BlockState, world: World, pos: BlockPos, random: RandomSource) {
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
        private val SHAPE = box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0)
        private val COLLISION_SHAPE = box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0)
    }
}
