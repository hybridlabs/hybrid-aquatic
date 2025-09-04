package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class NautilusEntity(entityType: EntityType<out NautilusEntity>, world: Level) :
    HybridAquaticCephalopodEntity(
        entityType,
        world,
        HybridAquaticEntityTags.NONE,
        HybridAquaticEntityTags.SHARK,
        false,
        false
    ) {

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }

    override fun registerGoals() {
        super.registerGoals()
        if (world.isDay) {
            goalSelector.addGoal(1, StayDeepGoal(this, 1.0, 1, 12))
        } else {
            goalSelector.addGoal(1, StayNearSurfaceGoal(this, 1.0, 1, 4))
        }
    }

    override fun getHurtSound(source: DamageSource?): SoundEvent {
        return SoundEvents._SHULKER_CLOSE
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents._SHULKER_HURT_CLOSED
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}