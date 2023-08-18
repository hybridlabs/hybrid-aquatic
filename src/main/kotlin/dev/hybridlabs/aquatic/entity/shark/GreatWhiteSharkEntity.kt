package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class GreatWhiteSharkEntity(entityType: EntityType<out GreatWhiteSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, HybridAquaticEntityTags.GREAT_WHITE_SHARK_PREY, false) {
        companion object {
            fun createMobAttributes(): DefaultAttributeContainer.Builder {
                return WaterCreatureEntity.createMobAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0)
                    .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.6)
                    .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                    .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
            }
        }
    }
