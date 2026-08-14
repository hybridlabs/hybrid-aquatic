package dev.hybridlabs.aquatic.entity.jellyfish

import dev.hybridlabs.hapi.entity.base.aquatic.BaseJellyfishEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level


class LionsManeJellyfishEntity(entityType: EntityType<out LionsManeJellyfishEntity>, world: Level) :
    BaseJellyfishEntity(entityType, world, true, 0) {

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 1.2)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 20.0)
        }
    }
}