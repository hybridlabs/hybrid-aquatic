package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.crustacean.YetiCrabEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.util.RandomSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DripstoneThickness
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("DEPRECATION", "SameParameterValue", "OVERRIDE_DEPRECATION")
class ThermalVentBlock(
    private val emitsParticles: Boolean,
    private val fireDamage: Int,
    settings: Properties?
) : Block(settings), SimpleWaterloggedBlock {

    init {
        this.registerDefaultState(stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, true)
            .setValue(THICKNESS, DripstoneThickness.TIP))
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val supportingPos = pos.below()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.`is`(this) || supportingState.isFaceSturdy(world, supportingPos, Direction.UP)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val world = ctx.level
        val pos = ctx.clickedPos
        return defaultBlockState()
            .setValue(THICKNESS, getThickness(world, pos))
            .setValue(WATERLOGGED, world.getFluidState(pos) == Fluids.WATER)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }

        return if (direction != Direction.DOWN && direction != Direction.UP) {
            state
        } else {
            state.setValue(THICKNESS, getThickness(world, pos))
        }
    }

    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        if (state.getValue(THICKNESS) == DripstoneThickness.TIP && state.getValue(WATERLOGGED)) {
            spawnSmokeParticle(world, pos, random)
        }
    }

    private fun getThickness(world: LevelReader, currentPos: BlockPos): DripstoneThickness{
        val blockAbove = world.getBlockState(currentPos.relative(Direction.UP))

        return if (blockAbove.`is`(this)) {
            val blockBelow = world.getBlockState(currentPos.relative(Direction.DOWN))
            if (blockBelow.`is`(this)) {
                DripstoneThickness.MIDDLE
            } else {
                DripstoneThickness.BASE
            }
        } else {
            DripstoneThickness.TIP
        }
    }

    private fun spawnSmokeParticle(world: Level, pos: BlockPos, random: RandomSource) {
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

    override fun stepOn(world: Level, pos: BlockPos, state: BlockState, entity: Entity) {
        if (world.isClientSide) return

        if (state.getValue(THICKNESS) == DripstoneThickness.TIP && state.getValue(WATERLOGGED) && entity !is YetiCrabEntity) {
            if (!entity.isSteppingCarefully && entity is LivingEntity && !EnchantmentHelper.hasFrostWalker(entity)) {
                entity.hurt(world.damageSources().hotFloor(), fireDamage.toFloat())
                entity.addEffect(MobEffectInstance(HybridAquaticMobEffects.CORROSION.get(), 200, 0))
            }
        }

        super.stepOn(world, pos, state, entity)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        val voxelShape = when (val thickness = state.getValue(THICKNESS) as DripstoneThickness) {
            DripstoneThickness.TIP -> TIP_COLLISION_SHAPE
            DripstoneThickness.MIDDLE -> MIDDLE_COLLISION_SHAPE
            DripstoneThickness.BASE -> BASE_COLLISION_SHAPE
            else -> throw IllegalStateException("Unexpected thickness: $thickness")
        }
        val vec3d = state.getOffset(world, pos)
        return voxelShape.move(vec3d.x, 0.0, vec3d.z)
    }

    override fun getOcclusionShape(state: BlockState, world: BlockGetter, pos: BlockPos): VoxelShape {
        return Shapes.empty()
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        val voxelShape = when (val thickness = state.getValue(THICKNESS) as DripstoneThickness) {
            DripstoneThickness.TIP -> TIP_SHAPE
            DripstoneThickness.MIDDLE -> MIDDLE_SHAPE
            DripstoneThickness.BASE -> BASE_SHAPE
            else -> throw IllegalStateException("Unexpected thickness: $thickness")
        }

        val modelOffset = state.getOffset(world, pos)
        return voxelShape.move(modelOffset.x, 0.0, modelOffset.z)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) {
            Fluids.WATER.getSource(false)
        } else {
            super.getFluidState(state)
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(THICKNESS, WATERLOGGED)
    }

    companion object {
        val THICKNESS: EnumProperty<DripstoneThickness> = EnumProperty.create("thickness", DripstoneThickness::class.java, DripstoneThickness.TIP, DripstoneThickness.MIDDLE, DripstoneThickness.BASE)
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        private val TIP_COLLISION_SHAPE = box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_COLLISION_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_COLLISION_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)

        private val TIP_SHAPE = box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_SHAPE = box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
    }
}
