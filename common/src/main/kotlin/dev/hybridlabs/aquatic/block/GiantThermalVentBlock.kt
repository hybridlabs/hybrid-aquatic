package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.GiantThermalVentBlockEntity
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.effect.HAMobEffects
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.util.StringRepresentable
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.BaseEntityBlock.createTickerHelper
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("DEPRECATION", "SameParameterValue", "OVERRIDE_DEPRECATION", "REDUNDANT_ELSE_IN_WHEN")
class GiantThermalVentBlock(
    private val fireDamage: Int,
    settings: Properties,
) : Block(settings), EntityBlock {

    init {
        this.registerDefaultState(
            stateDefinition.any().setValue(THICKNESS, GiantThermalVentPosition.TIP)
        )
    }

    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
        return GiantThermalVentBlockEntity(blockPos, blockState)
    }

    override fun <T : BlockEntity> getTicker(
        level: Level,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if (level.isClientSide) {
            createTickerHelper(
                type,
                HABlockEntityTypes.GIANT_THERMAL_VENT.get(),
                GiantThermalVentBlockEntity::particleTick
            )
        } else {
            null
        }
    }

    override fun onPlace(state: BlockState, world: Level, pos: BlockPos, oldState: BlockState, movedByPiston: Boolean) {
        if (!world.isClientSide) {
            world.scheduleTick(pos, this, 24000)
        }
        super.onPlace(state, world, pos, oldState, movedByPiston)
    }

    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (level.isClientSide) return

        level.scheduleTick(pos, this, 24000)

        if (
            state.getValue(THICKNESS) != GiantThermalVentPosition.BASE &&
            state.getValue(THICKNESS) != GiantThermalVentPosition.TIP
        ) return

        val below = level.getBlockState(pos.below())
        if (!below.`is`(Blocks.MAGMA_BLOCK)) return

        var cursor = pos
        while (level.getBlockState(cursor.above()).`is`(this)) {
            cursor = cursor.above()
        }

        val aboveTip = cursor.above()

        if (!level.getFluidState(aboveTip).`is`(Fluids.WATER)) return
        if (!level.getBlockState(aboveTip).isAir && !level.getBlockState(aboveTip).`is`(Blocks.WATER)) return

        level.setBlock(
            aboveTip,
            defaultBlockState(),
            UPDATE_ALL
        )
    }

    override fun isPathfindable(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        type: PathComputationType,
    ): Boolean {
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
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos,
    ): BlockState {

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
        if (state.getValue(THICKNESS) == GiantThermalVentPosition.TIP) {
            addAlwaysVisibleParticle(world, pos, random)
        }
    }

    private fun getThickness(world: LevelReader, currentPos: BlockPos): GiantThermalVentPosition {
        val blockAbove = world.getBlockState(currentPos.relative(Direction.UP))

        return if (blockAbove.`is`(this)) {
            val blockBelow = world.getBlockState(currentPos.relative(Direction.DOWN))
            if (blockBelow.`is`(this)) {
                GiantThermalVentPosition.MIDDLE
            } else {
                GiantThermalVentPosition.BASE
            }
        } else {
            GiantThermalVentPosition.TIP
        }
    }

    private fun addAlwaysVisibleParticle(world: Level, pos: BlockPos, random: RandomSource) {
        world.addAlwaysVisibleParticle(
            ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
            pos.x.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            pos.y.toDouble() + 1.0,
            pos.z.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            0.0,
            0.05,
            0.0
        )
    }

    override fun stepOn(world: Level, pos: BlockPos, state: BlockState, entity: Entity) {
        if (world.isClientSide) return

        if (state.getValue(THICKNESS) == GiantThermalVentPosition.TIP) {
            if (entity is Player && !entity.isInvulnerableTo(world.damageSources().hotFloor())) {
                entity.hurt(world.damageSources().hotFloor(), fireDamage.toFloat())
                entity.addEffect(MobEffectInstance(HAMobEffects.CORROSION.get(), 200, 0))
            }
        }

        super.stepOn(world, pos, state, entity)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(THICKNESS)
    }

    companion object {
        val THICKNESS: EnumProperty<GiantThermalVentPosition> = EnumProperty.create(
            "thickness",
            GiantThermalVentPosition::class.java,
            GiantThermalVentPosition.TIP,
            GiantThermalVentPosition.MIDDLE,
            GiantThermalVentPosition.BASE
        )

        private val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)

        fun makeParticles(
            level: Level,
            pos: BlockPos,
        ) {
            val random = level.random

            val particle = ParticleTypes.CAMPFIRE_SIGNAL_SMOKE

            level.addParticle(
                particle,
                false,
                pos.x + 0.5 + random.nextDouble() / 3.0 * (if (random.nextBoolean()) 1 else -1),
                pos.y + random.nextDouble() + random.nextDouble(),
                pos.z + 0.5 + random.nextDouble() / 3.0 * (if (random.nextBoolean()) 1 else -1),
                0.0,
                0.03,
                0.0
            )
        }
    }

    enum class GiantThermalVentPosition : StringRepresentable {
        TIP, MIDDLE, BASE;

        override fun getSerializedName(): String {
            return name.lowercase()
        }
    }
}