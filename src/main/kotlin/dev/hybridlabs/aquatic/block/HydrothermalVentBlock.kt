package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.HydrothermalVentBlockEntity
import dev.hybridlabs.aquatic.entity.critter.YetiCrabEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess

@Suppress("DEPRECATION")
class HydrothermalVentBlock(settings: Settings) : PlantBlock(settings), BlockEntityProvider, Waterloggable {
    init {
        defaultState = stateManager.defaultState.with(Properties.WATERLOGGED, false)
    }

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (!world.isClient && entity is LivingEntity && entity !is YetiCrabEntity) {
            entity.addStatusEffect(StatusEffectInstance(StatusEffects.WITHER, 100, 1))
            entity.addStatusEffect(StatusEffectInstance(StatusEffects.DARKNESS, 100, 2))
        }
    }

    override fun randomDisplayTick(state: BlockState?, world: World?, pos: BlockPos?, random: Random?) {
        super.randomDisplayTick(state, world, pos, random)
        if (world != null && pos != null) {
            spawnSmokeParticle(world, pos)
        }
    }
    private fun spawnSmokeParticle(world: World, pos: BlockPos) {
        val random = world.random
        val defaultParticleType = ParticleTypes.CAMPFIRE_COSY_SMOKE

        world.addParticle(
            defaultParticleType,
            pos.x.toDouble() + 0.5 + random.nextDouble() / 3.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            pos.y.toDouble() + random.nextDouble() + random.nextDouble(),
            pos.z.toDouble() + 0.5 + random.nextDouble() / 3.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            0.0,
            0.07,
            0.0
        )
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty || floor.isSideSolidFullSquare(world, pos, Direction.UP)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return COLLISION_SHAPE
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

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape {
        return SHAPE
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER)) defaultState.with(Properties.WATERLOGGED, ctx.world.getFluidState(ctx.blockPos).isOf(Fluids.WATER)) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return HydrothermalVentBlockEntity(pos, state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.WATERLOGGED)
    }

    companion object {
        private val SHAPE = createCuboidShape(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)
        private val COLLISION_SHAPE = createCuboidShape(2.0, 1.0, 2.0, 14.0, 12.0, 14.0)
    }
}