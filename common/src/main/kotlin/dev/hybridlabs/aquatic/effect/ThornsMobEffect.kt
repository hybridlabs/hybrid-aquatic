package dev.hybridlabs.aquatic.effect

import net.minecraft.server.level.ServerLevel

import net.minecraft.tags.DamageTypeTags
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageTypes
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity

class ThornsMobEffect : MobEffect(MobEffectCategory.BENEFICIAL, 0x695672) {

    override fun shouldApplyEffectTickThisTick(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyEffectTick(level: ServerLevel, entity: LivingEntity, amplifier: Int): Boolean {
        val strength = amplifier + 1
        val damageSource = entity.lastDamageSource ?: return true
        val attacker = damageSource.directEntity as? LivingEntity ?: return true

        if (attacker.isDeadOrDying) return true
        if (damageSource.`is`(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) || damageSource.`is`(DamageTypes.THORNS)) return true

        if (shouldHit(strength, entity.random)) {
            attacker.hurtServer(level, entity.damageSources().thorns(entity), getDamage(strength))
        }
        return true
    }

    private fun shouldHit(level: Int, random: RandomSource): Boolean {
        return if (level <= 0) {
            false
        } else {
            random.nextFloat() < 0.15f * level.toFloat()
        }
    }

    private fun getDamage(level: Int): Float {
        return when {
            level <= 0 -> 0f
            level <= 3 -> 1f
            level <= 6 -> 2f
            level <= 9 -> 3f
            else -> (level - 6).toFloat()
        }
    }
}