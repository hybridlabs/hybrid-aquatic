package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class BullSharkEntity(entityType: EntityType<out BullSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, HybridAquaticEntityTags.BULL_SHARK_PREY, false, false, true, true, true) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 36.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)

        }
    }
    override fun getMaxSize() : Int {
        return 5
    }
    override fun getMinSize(): Int {
        return -5
    }
}
