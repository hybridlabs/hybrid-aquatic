package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.crustacean.YetiCrabEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.CollisionContext
import net.minecraft.block.SimpleWaterloggedBlcok
import net.minecraft.block.enums.Thickness
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.pathing.PathComputationType
import net.minecraft.entity.effect.MobEffectInstance
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockPlaceContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.Shapes
import net.minecraft.world.BlockGetter
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader

@Suppress("DEPRECATION", "SameParameterValue", "OVERRIDE_DEPRECATION")
class ThermalVentBlock(
    private val emitsParticles: Boolean,
    private val fireDamage: Int,
    settings: Settings?
) : Block(settings), SimpleWaterloggedBlcok {

    init {
        defaultBlockState() = stateManager.defaultBlockState()
            .with(THICKNESS, Thickness.TIP)
            .with(WATERLOGGED, true)
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val supportingPos = pos.below()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.isOf(this) || supportingState.isFaceSturdy(world, supportingPos, Direction.UP)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val world = ctx.world
        val pos = ctx.blockPos
        return defaultBlockState()
            .with(THICKNESS, getThickness(world, pos))
            .with(WATERLOGGED, world.getFluidState(pos).fluid == Fluids.WATER)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }

        return if (direction != Direction.DOWN && direction != Direction.UP) {
            state
        } else {
            state.with(THICKNESS, getThickness(world, pos))
        }
    }

    override fun animateTick(state: BlockState, world: World, pos: BlockPos, random: RandomSource) {
        if (state.get(THICKNESS) == Thickness.TIP && state.get(WATERLOGGED)) {
            spawnSmokeParticle(world, pos, random)
        }
    }

    private fun getThickness(world: LevelReader, currentPos: BlockPos): Thickness {
        val blockAbove = world.getBlockState(currentPos.offset(Direction.UP))

        return if (blockAbove.isOf(this)) {
            val blockBelow = world.getBlockState(currentPos.offset(Direction.DOWN))
            if (blockBelow.isOf(this)) {
                Thickness.MIDDLE
            } else {
                Thickness.BASE
            }
        } else {
            Thickness.TIP
        }
    }

    private fun spawnSmokeParticle(world: World, pos: BlockPos, random: RandomSource) {
        world.addParticle(
            ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
            pos.x.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            pos.y.toDouble() + 0.4,
            pos.z.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            0.0,
            0.01,
            0.0
        )
    }

    override fun onSteppedOn(world: World, pos: BlockPos?, state: BlockState?, entity: Entity) {
        if (world.isClientSide || pos == null || state == null) return

        if (state.get(THICKNESS) == Thickness.TIP && state.get(WATERLOGGED) && entity !is YetiCrabEntity) {
            if (!entity.bypassesSteppingEffects() && entity is LivingEntity && !EnchantmentHelper.hasFrostWalker(entity)) {
                entity.damage(world.damageSources.hotFloor(), fireDamage.toFloat())
                entity.addMobEffect(MobEffectInstance(HybridAquaticMobEffects.CORROSION, 200, 0))
            }
        }

        super.onSteppedOn(world, pos, state, entity)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter?,
        pos: BlockPos?,
        context: CollisionContext?
    ): VoxelShape {
        val voxelShape = when (val thickness = state.get(THICKNESS) as Thickness) {
            Thickness.TIP -> TIP_COLLISION_SHAPE
            Thickness.MIDDLE -> MIDDLE_COLLISION_SHAPE
            Thickness.BASE -> BASE_COLLISION_SHAPE
            else -> throw IllegalStateException("Unexpected thickness: $thickness")
        }
        val vec3d = state.getModelOffset(world, pos)
        return voxelShape.offset(vec3d.x, 0.0, vec3d.z)
    }

    override fun getCullingShape(state: BlockState, world: BlockGetter, pos: BlockPos): VoxelShape {
        return Shapes.empty()
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext?
    ): VoxelShape {
        val voxelShape = when (val thickness = state.get(THICKNESS) as Thickness) {
            Thickness.TIP -> TIP_SHAPE
            Thickness.MIDDLE -> MIDDLE_SHAPE
            Thickness.BASE -> BASE_SHAPE
            else -> throw IllegalStateException("Unexpected thickness: $thickness")
        }

        val modelOffset = state.getModelOffset(world, pos)
        return voxelShape.offset(modelOffset.x, 0.0, modelOffset.z)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) {
            Fluids.WATER.getSource(false)
        } else {
            super.getFluidState(state)
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
        builder.add(THICKNESS, WATERLOGGED)
    }

    companion object {
        val THICKNESS: EnumProperty<Thickness> = EnumProperty.of("thickness", Thickness::class.java, Thickness.TIP, Thickness.MIDDLE, Thickness.BASE)
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED

        private val TIP_COLLISION_SHAPE = box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_COLLISION_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_COLLISION_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)

        private val TIP_SHAPE = box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
    }
}
