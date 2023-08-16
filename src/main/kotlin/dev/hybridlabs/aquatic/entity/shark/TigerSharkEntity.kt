package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.HybridAquatic.MOD_ID
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.World

class TigerSharkEntity(entityType: EntityType<out TigerSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, PREY_TAG, false) {
    companion object {
        val PREY_TAG = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier(MOD_ID, "prey/tiger_shark"))!!
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)

        }
    }
}