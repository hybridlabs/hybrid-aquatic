package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class NautilusEntity(entityType: EntityType<out NautilusEntity>, world: Level) :
    HybridAquaticCephalopodEntity(
        entityType,
        world,
        emptyMap(),
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

    override fun getHurtSound(damageSource: DamageSource): SoundEvent {
        return SoundEvents.SHULKER_CLOSE
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SHULKER_HURT_CLOSED
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}