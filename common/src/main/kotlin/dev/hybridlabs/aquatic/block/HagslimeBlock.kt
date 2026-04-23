package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.SlimeBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.EntityCollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class HagslimeBlock(settings: Properties): SlimeBlock(settings) {
    init {
        this.registerDefaultState(stateDefinition.any())
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return Shapes.block()
    }

    override fun isPathfindable(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        type: PathComputationType
    ): Boolean {
        return false
    }

    override fun getCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        if (context is EntityCollisionContext) {
            val entity = context.entity

            if (entity != null) {
                if (entity !is Player) {
                    return Shapes.block()
                }

                if (!entity.isCrouching) {
                    return Shapes.block()
                }
            }
        }

        return super.getCollisionShape(state, level, pos, context)
    }

    override fun fallOn(level: Level, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Float) {
        if (entity is Player && entity.isCrouching) {
            super.fallOn(level, state, pos, entity, fallDistance)
        } else {
            entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall())
        }
    }

    override fun updateEntityAfterFallOn(level: BlockGetter, entity: Entity) {
        if (entity is Player && entity.isCrouching) {
            super.updateEntityAfterFallOn(level, entity)
        } else {
            bounceUp(entity)
        }
    }

    private fun bounceUp(entity: Entity) {
        val vec = entity.deltaMovement
        if (vec.y < 0.0) {
            val multiplier = if (entity is LivingEntity) 1.0 else 0.8
            entity.setDeltaMovement(vec.x, -vec.y * multiplier, vec.z)
        }
    }
}