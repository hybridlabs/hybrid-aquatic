package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.shark.behaviour.HostileSharkBehaviour
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class ThresherSharkEntity(entityType: EntityType<out ThresherSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, HybridAquaticEntityTags.THRESHER_SHARK_PREY, HostileSharkBehaviour(false, false, true, true)) {
        companion object {
            fun createMobAttributes(): DefaultAttributeContainer.Builder {
                return WaterCreatureEntity.createMobAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0)
                    .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.3)
                    .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                    .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)

            }
        }
    }
