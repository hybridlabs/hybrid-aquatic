package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

class FlashlightFishEntity(entityType: EntityType<out FlashlightFishEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(entityType, world, HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.FLASHLIGHT_FISH_PREDATOR) {

    var isLightOn: Boolean = true

    override fun tick() {
        super.tick()
        checkNearbyEntities()
    }

    private fun checkNearbyEntities() {
        val detectionRadius = 4.0
        val nearbyEntities = world.getEntitiesByClass(LivingEntity::class.java, boundingBox.expand(detectionRadius)) {
            it is PlayerEntity
        }

        isLightOn = nearbyEntities.isEmpty()
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 3.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.FOLLOW_RANGE, 12.0)
                .add(EntityAttributes.STEP_HEIGHT, 1.0)
        }
    }
}
