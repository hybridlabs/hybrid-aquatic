package dev.hybridlabs.aquatic.entity.fish.deepsea

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class OarfishEntity(entityType: EntityType<out OarfishEntity>, world: World) :
    HybridAquaticDeepSeaFishEntity(entityType, world, emptyMap(), HybridAquaticEntityTags.OARFISH_PREY, HybridAquaticEntityTags.OARFISH_PREDATOR) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }
}
