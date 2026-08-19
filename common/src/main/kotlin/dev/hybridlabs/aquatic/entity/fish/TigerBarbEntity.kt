package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.boids.BoidGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.boids.StayInWaterGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSchoolingFishEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class TigerBarbEntity(type: EntityType<out TigerBarbEntity>, world: Level) :
    BaseSchoolingFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.ALL_SHARKS
    )

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(3, StayInWaterGoal(this))
    }

    override fun canCollideWith(entity: Entity): Boolean {
        if (entity is TigerBarbEntity) {
            return false
        }
        return super.canCollideWith(entity)
    }

    override fun doPush(entity: Entity) {
        if (entity is TigerBarbEntity) {
            return
        }
        super.doPush(entity)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 3
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}
