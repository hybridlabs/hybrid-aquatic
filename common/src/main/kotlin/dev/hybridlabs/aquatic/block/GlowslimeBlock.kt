package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.impl.StickyBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HalfTransparentBlock
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.abs

class GlowslimeBlock(properties: Properties) : HalfTransparentBlock(properties), StickyBlock {
    override fun fallOn(level: Level, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Float) {
        if (entity.isSuppressingBounce) {
            super.fallOn(level, state, pos, entity, fallDistance)
        } else {
            entity.causeFallDamage(fallDistance, 0.0f, level.damageSources().fall())
        }
    }

    override fun updateEntityAfterFallOn(level: BlockGetter, entity: Entity) {
        if (entity.isSuppressingBounce) {
            super.updateEntityAfterFallOn(level, entity)
        } else {
            this.bounceUp(entity)
        }
    }

    private fun bounceUp(entity: Entity) {
        val vec3 = entity.deltaMovement
        if (vec3.y < 0.0) {
            val d0 = if (entity is LivingEntity) 1.0 else 0.8
            entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z)
        }
    }

    override fun stepOn(level: Level, pos: BlockPos, state: BlockState, entity: Entity) {
        val d0 = abs(entity.deltaMovement.y)
        if (d0 < 0.1 && !entity.isSteppingCarefully) {
            val d1 = 0.4 + d0 * 0.2
            entity.deltaMovement = entity.deltaMovement.multiply(d1, 1.0, d1)
        }

        super.stepOn(level, pos, state, entity)
    }

    override fun isSticky(): Boolean {
        return true
    }

    override fun isStickyToNeighbor(neighbor: BlockState): Boolean {
        return neighbor.`is`(this) ||
                !(neighbor.`is`(Blocks.HONEY_BLOCK) || neighbor.`is`(Blocks.SLIME_BLOCK) || neighbor.block is StickyBlock)
    }
}