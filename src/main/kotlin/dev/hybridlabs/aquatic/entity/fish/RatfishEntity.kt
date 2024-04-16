package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class RatfishEntity(entityType: EntityType<out RatfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, 2, HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
        }
    }
}