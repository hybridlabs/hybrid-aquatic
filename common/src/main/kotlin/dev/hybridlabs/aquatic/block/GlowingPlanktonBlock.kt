@file:Suppress("DEPRECATION")

package dev.hybridlabs.aquatic.block

import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.CollisionContext
import net.minecraft.block.SimpleWaterloggedBlcok
import net.minecraft.entity.Entity
import net.minecraft.entity.ai.pathing.PathComputationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerLevel
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader

@Suppress("OVERRIDE_DEPRECATION")
class GlowingPlanktonBlock(settings: Properties) : Block(
    settings.luminance { state -> state.get(LIGHT_LEVEL) }
), SimpleWaterloggedBlcok {
    init {
        defaultBlockState() = defaultBlockState()
            .with(WATERLOGGED, true)
            .with(LIT, false)
            .with(LIGHT_LEVEL, 0) as BlockState
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.above())
        if (fluidStateAbove.fluid != Fluids.EMPTY) {
            return false
        }
        val stateBelow = world.getBlockState(pos.down())
        if (stateBelow.block == this) {
            return false
        }
        val fluidState = world.getFluidState(pos)
        return fluidState.fluid == Fluids.WATER
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext?
    ): VoxelShape {
        return SHAPE
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val world = context.world
        val pos = context.blockPos
        val fluidState = world.getFluidState(pos)
        return if (fluidState.fluid == Fluids.WATER) {
            super.getStateForPlacement(context)?.with(WATERLOGGED, true)
        } else {
            null
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun isTransparent(state: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return true
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.INVISIBLE
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
        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        super.onEntityCollision(state, world, pos, entity)
        if (world is ServerLevel && !state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, true).with(LIGHT_LEVEL, 7))
            world.scheduleBlockTick(pos, this, 20)
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

                world.spawnParticles(
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

    override fun scheduledTick(
        state: BlockState,
        world: ServerLevel,
        pos: BlockPos,
        random: net.minecraft.util.math.random.Random
    ) {
        val lightLevel = state.get(LIGHT_LEVEL)
        if (lightLevel > 0) {
            world.setBlockState(pos, state.with(LIGHT_LEVEL, lightLevel - 1))
            world.scheduleBlockTick(pos, this, 20)
        } else if (state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, false))
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED, LIT, LIGHT_LEVEL)
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return true
    }

    companion object {
        val LIT: BooleanProperty = BooleanProperty.of("lit")
        val LIGHT_LEVEL: net.minecraft.state.property.IntProperty = net.minecraft.state.property.IntProperty.of("light_level", 0, 7)
        private val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }
}
