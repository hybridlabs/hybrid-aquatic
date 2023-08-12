package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic.MOD_ID
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.World

class BaskingSharkEntity(entityType: EntityType<out BaskingSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, PREY_TAG, false) {
    companion object {
        val PREY_TAG = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier(MOD_ID, "prey/basking_shark"))!!
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 60.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)

        }
    }
}
