package dev.hybridlabs.aquatic.effect

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

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int): Boolean {
        val level = amplifier + 1
        val damageSource = entity.lastDamageSource ?: return false
        val attacker = damageSource.directEntity as? LivingEntity ?: return false

        if (attacker.isDeadOrDying) return false
        if (damageSource.`is`(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) || damageSource.`is`(DamageTypes.THORNS)) return false

        if (shouldHit(level, entity.random)) {
            attacker.hurt(entity.damageSources().thorns(entity), getDamage(level))
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