package dev.hybridlabs.aquatic.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class BleedingStatusEffect : StatusEffect(StatusEffectCategory.HARMFUL, 0xee4049) {

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 40 == 0
    }

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        if (entity.health > entity.maxHealth / 1.3) {
            entity.health = maxOf(entity.maxHealth / 1.3F)
        }
    }
}
