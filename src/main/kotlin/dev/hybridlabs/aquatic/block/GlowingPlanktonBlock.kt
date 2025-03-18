@file:Suppress("DEPRECATION")

package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.entity.Entity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

@Suppress("OVERRIDE_DEPRECATION")
class GlowingPlanktonBlock(settings: Settings) : Block(
    settings.luminance { state -> state.get(LIGHT_LEVEL) }
), Waterloggable {
    init {
        defaultState = defaultState
            .with(WATERLOGGED, true)
            .with(LIT, false)
            .with(LIGHT_LEVEL, 0) as BlockState
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.up())
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

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        val world = context.world
        val pos = context.blockPos
        val fluidState = world.getFluidState(pos)
        return if (fluidState.fluid == Fluids.WATER) {
            super.getPlacementState(context)?.with(WATERLOGGED, true)
        } else {
            null
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun isTransparent(state: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return true
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.INVISIBLE
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
        if (!canPlaceAt(state, world, pos)) {
            return Blocks.AIR.defaultState
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        super.onEntityCollision(state, world, pos, entity)
        if (world is ServerWorld && !state.get(LIT)) {
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
        world: ServerWorld,
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

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return true
    }

    companion object {
        val LIT: BooleanProperty = BooleanProperty.of("lit")
        val LIGHT_LEVEL: net.minecraft.state.property.IntProperty = net.minecraft.state.property.IntProperty.of("light_level", 0, 7)
        private val SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }
}