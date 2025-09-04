package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.world.World

class FireflySquidEntity(entityType: EntityType<out FireflySquidEntity>, world: Level) :
    HybridAquaticCephalopodEntity(
        entityType,
        world,
        HybridAquaticEntityTags.CRUSTACEAN,
        HybridAquaticEntityTags.SHARK,
        true,
        true
    ) {

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
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}