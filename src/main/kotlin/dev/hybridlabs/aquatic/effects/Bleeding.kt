package dev.hybridlabs.aquatic.effects

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class Bleeding : StatusEffect(StatusEffectCategory.HARMFUL, 0xee4049) {

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 40 == 0
    }

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        val targetHealth = entity.maxHealth / 2.0f
        if (entity.health > targetHealth) {
            entity.health = maxOf(entity.health - 2.0f, targetHealth)
        }
    }
}