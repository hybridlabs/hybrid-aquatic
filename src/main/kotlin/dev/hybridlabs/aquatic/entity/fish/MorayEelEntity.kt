package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class MorayEelEntity(entityType: EntityType<out MorayEelEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, emptyMap(), HybridAquaticEntityTags.MORAY_EEL_PREY, HybridAquaticEntityTags.MORAY_EEL_PREDATOR) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}
