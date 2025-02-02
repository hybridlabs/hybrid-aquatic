package dev.hybridlabs.aquatic.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class ToxicShockStatusEffect : StatusEffect(StatusEffectCategory.HARMFUL, 0xee4049) {

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 20 == 0
    }

    override fun onApplied(entity: LivingEntity, amplifier: Int) {
        entity.damage(entity.damageSources.wither(), 2.0f * (amplifier + 1).toFloat())
    }
}
