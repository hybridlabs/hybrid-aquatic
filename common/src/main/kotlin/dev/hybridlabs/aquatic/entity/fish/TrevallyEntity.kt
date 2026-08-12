package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.water.base.BaseSchoolingFishEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class TrevallyEntity(type: EntityType<out TrevallyEntity>, world: Level) :
    BaseSchoolingFishEntity(type, world) {

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun registerGoals() {
        super.registerGoals()
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
}
