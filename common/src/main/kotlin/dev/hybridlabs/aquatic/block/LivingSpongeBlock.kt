package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
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
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class LivingSpongeBlock(
    private val emitsParticles: Boolean,
    settings: Properties
) : BushBlock(settings), SimpleWaterloggedBlock {

    private var bubbleTimer = 0

    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, true))
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
        if (state.getValue(WATERLOGGED)) {
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

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return SHAPE
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        return if (fluidState.`is`(FluidTags.WATER)) defaultBlockState().setValue(
            WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).`is`(
                Fluids.WATER)) else null
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        if (state.getValue(WATERLOGGED) && emitsParticles && bubbleTimer % 20 == 0) {
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