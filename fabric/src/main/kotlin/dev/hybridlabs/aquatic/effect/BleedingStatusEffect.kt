package dev.hybridlabs.aquatic.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity

class BleedingMobEffect : MobEffect(MobEffectCategory.HARMFUL, 0xee4049) {

    override fun isDurationEffectTick(duration: Int, amplifier: Int): Boolean {
        return duration % 40 == 0
    }

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int) {
        if (entity.health > entity.maxHealth / 1.3) {
            entity.health = maxOf(entity.maxHealth / 1.3F)
        }
    }
}
