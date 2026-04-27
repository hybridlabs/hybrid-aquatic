package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.impl.StickyBlock
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.PrimedTnt
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.AbstractMinecart
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.HalfTransparentBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.EntityCollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import kotlin.math.abs

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class HagslimeBlock(settings: Properties) : HalfTransparentBlock(settings), StickyBlock {
    init {
        this.registerDefaultState(stateDefinition.any())
    }

    override fun getOcclusionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
    ): VoxelShape {
        return Shapes.empty()
    }

    override fun skipRendering(
        state: BlockState,
        adjacentState: BlockState,
        direction: Direction,
    ): Boolean {
        return if (adjacentState.`is`(this)) true
        else super.skipRendering(state, adjacentState, direction)
    }

    override fun getVisualShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return Shapes.empty()
    }

    override fun getCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        if (context !is EntityCollisionContext) return SHAPE

        val entity = context.entity
        if (entity == null || entity !is Player) return SHAPE

        return if (entity.isCrouching || context.isDescending) Shapes.empty() else SHAPE
    }

    override fun isPathfindable(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        type: PathComputationType,
    ): Boolean {
        return true
    }

    override fun fallOn(level: Level, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Float) {
        if (entity is Player && entity.isCrouching) {
            super.fallOn(level, state, pos, entity, fallDistance)
        } else {
            entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0f, 1.0f)
            if (!level.isClientSide) {
                level.broadcastEntityEvent(entity, 54.toByte())
            }

            if (entity.causeFallDamage(fallDistance, 0.2f, level.damageSources().fall())) {
                entity.playSound(
                    this.soundType.fallSound,
                    this.soundType.getVolume() * 0.5f,
                    this.soundType.getPitch() * 0.75f
                )
            }
        }
    }

    override fun entityInside(state: BlockState, level: Level, pos: BlockPos, entity: Entity) {
        if (entity is Player && entity.isCrouching) {
            return
        }

        if (this.isSlidingDown(pos, entity)) {
            this.maybeDoSlideAchievement(entity, pos)
            this.doSlideMovement(entity)
            this.maybeDoSlideEffects(level, entity)
        }

        super.entityInside(state, level, pos, entity)
    }

    private fun maybeDoSlideAchievement(entity: Entity?, pos: BlockPos) {
        if (entity is ServerPlayer && entity.level().gameTime % 20L == 0L) {
            CriteriaTriggers.HONEY_BLOCK_SLIDE.trigger(entity, entity.level().getBlockState(pos))
        }
    }

    private fun doSlideMovement(entity: Entity) {
        val vec3 = entity.deltaMovement
        if (vec3.y < -0.13) {
            val d0 = -0.05 / vec3.y
            entity.deltaMovement = Vec3(vec3.x * d0, -0.05, vec3.z * d0)
        } else {
            entity.deltaMovement = Vec3(vec3.x, -0.05, vec3.z)
        }

        entity.resetFallDistance()
    }

    private fun doesEntityDoHagslimeBlockSlideEffects(entity: Entity?): Boolean {
        return entity is LivingEntity || entity is AbstractMinecart || entity is PrimedTnt || entity is Boat
    }

    private fun maybeDoSlideEffects(level: Level, entity: Entity) {
        if (doesEntityDoHagslimeBlockSlideEffects(entity)) {
            if (level.random.nextInt(5) == 0) {
                entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0f, 1.0f)
            }

            if (!level.isClientSide && level.random.nextInt(5) == 0) {
                level.broadcastEntityEvent(entity, 53.toByte())
            }
        }
    }

    private fun isSlidingDown(pos: BlockPos, entity: Entity): Boolean {
        if (entity.onGround()) {
            return false
        } else if (entity.y > pos.y.toDouble() + 0.9375 - 1.0E-7) {
            return false
        } else if (entity.deltaMovement.y >= -0.08) {
            return false
        } else {
            val d0 = abs(pos.x.toDouble() + 0.5 - entity.x)
            val d1 = abs(pos.z.toDouble() + 0.5 - entity.z)
            val d2 = 0.4375 + (entity.bbWidth / 2.0f).toDouble()
            return d0 + 1.0E-7 > d2 || d1 + 1.0E-7 > d2
        }
    }

    override fun isSticky(): Boolean {
        return true
    }

    override fun isStickyToNeighbor(neighbor: BlockState): Boolean {
        return !neighbor.`is`(this)
    }

    companion object {
        val SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0)
    }
}