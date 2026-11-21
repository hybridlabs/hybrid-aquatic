package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.impl.StickyBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HalfTransparentBlock
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.abs

open class GlowslimeBlock(properties: Properties) : HalfTransparentBlock(properties), StickyBlock {
    override fun fallOn(level: Level, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Double) {
        if (entity.isSuppressingBounce) {
            super.fallOn(level, state, pos, entity, fallDistance)
        } else {
            entity.causeFallDamage(fallDistance, 0.0f, level.damageSources().fall())
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