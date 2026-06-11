package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class GlowingPlanktonBlock(settings: Properties) : Block(
    settings.lightLevel { state -> state.getValue(LIGHT_LEVEL) }
), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any().setValue(WATERLOGGED, true)
                .setValue(LIT, false)
                .setValue(LIGHT_LEVEL, 0) as BlockState
        )
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
        return fluidState == Fluids.WATER.getSource(false)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val world = context.level
        val pos = context.clickedPos
        val fluidState = world.getFluidState(pos)
        return if (fluidState == Fluids.WATER.getSource(false)) {
            super.getStateForPlacement(context)?.setValue(WATERLOGGED, true)
        } else {
            null
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun propagatesSkylightDown(state: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return true
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.INVISIBLE
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }
        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun entityInside(state: BlockState, world: Level, pos: BlockPos, entity: Entity) {
        if (world is ServerLevel && !state.getValue(LIT)) {
            world.setBlockAndUpdate(pos, state.setValue(LIT, true).setValue(LIGHT_LEVEL, 7))
            world.scheduleTick(pos, this, 20)
            val radius = 1.5
            val particleCount = 5
            val random = world.random

            for (i in 0 until particleCount) {
                val offsetX = random.nextDouble() * 2 * radius - radius
                val offsetY = random.nextDouble() * 0.25
                val offsetZ = random.nextDouble() * 2 * radius - radius

                val particleX = entity.x + offsetX
                val particleY = entity.y + offsetY
                val particleZ = entity.z + offsetZ

                world.sendParticles(
                    ParticleTypes.GLOW,
                    particleX,
                    particleY,
                    particleZ,
                    particleCount,
                    0.0,
                    0.0,
                    0.0,
                    0.0
                )
            }
        }
    }

    override fun tick(
        state: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        random: RandomSource,
    ) {
        val lightLevel = state.getValue(LIGHT_LEVEL)
        if (lightLevel > 0) {
            world.setBlockAndUpdate(pos, state.setValue(LIGHT_LEVEL, lightLevel - 1))
            world.scheduleTick(pos, this, 20)
        } else if (state.getValue(LIT)) {
            world.setBlockAndUpdate(pos, state.setValue(LIT, false))
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED, LIT, LIGHT_LEVEL)
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return true
    }

    companion object {
        val LIT: BooleanProperty = BooleanProperty.create("lit")
        val LIGHT_LEVEL:
                IntegerProperty = IntegerProperty.create("light_level", 0, 7)
        private val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }
}