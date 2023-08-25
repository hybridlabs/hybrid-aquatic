package dev.hybridlabs.aquatic.entity.jellyfish

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class SeaNettleEntity(entityType: EntityType<out SeaNettleEntity>, world: World) : HybridAquaticJellyfishEntity(entityType, world) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
        }
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_SLIME_HURT_SMALL
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_DEATH_SMALL
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        return if (world.isClient) {
            false
        } else {
            if (!source.isIn(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) && !source.isOf(DamageTypes.THORNS)) {
                val attacker = source.source
                if (attacker is LivingEntity) {
                    attacker.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 200, 0), this)
                }
            }
            super.damage(source, amount)
        }
    }
    override fun onPlayerCollision(player: PlayerEntity) {
        super.onPlayerCollision(player)
        player.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 200, 0), this)
    }
}