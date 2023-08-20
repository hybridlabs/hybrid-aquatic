package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class FrilledSharkEntity(entityType: EntityType<out FrilledSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, HybridAquaticEntityTags.FRILLED_SHARK_PREY, false, false, false, true, true) {
        companion object {
            fun createMobAttributes(): DefaultAttributeContainer.Builder {
                return WaterCreatureEntity.createMobAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                    .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                    .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                    .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)

            }
        }
    }
