package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.player.Player
import net.minecraft.world.World

class FlashlightFishEntity(entityType: EntityType<out FlashlightFishEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(entityType, world,
        listOf(HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK)) {

    var isLightOn: Boolean = true

    override fun tick() {
        super.tick()
        checkNearbyEntities()
    }

    private fun checkNearbyEntities() {
        val detectionRadius = 4.0
        val nearbyEntities = world.getEntitiesByClass(LivingEntity::class.java, boundingBox.expand(detectionRadius)) {
            it isPlayer
        }

        isLightOn = nearbyEntities.isEmpty()
    }

    override fun getSpawnClusterSize(): Int {
        return 4
    }

    override fun registerGoals() {
        super.registerGoals()
        if (world.isDay) {
            goalSelector.addGoal(1, StayDeepGoal(this, 1.0, 1, 8))
        } else {
            goalSelector.addGoal(1, StayNearSurfaceGoal(this, 1.0, 1, 4))
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}