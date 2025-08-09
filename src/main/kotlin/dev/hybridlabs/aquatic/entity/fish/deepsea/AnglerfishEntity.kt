package dev.hybridlabs.aquatic.entity.fish.deepsea

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class AnglerfishEntity(entityType: EntityType<out AnglerfishEntity>, world: World) :
    HybridAquaticDeepSeaFishEntity(entityType, world, emptyMap(), HybridAquaticEntityTags.ANGLERFISH_PREY, HybridAquaticEntityTags.ANGLERFISH_PREDATOR) {

    override fun getLimitPerChunk(): Int {
        return 3
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}
