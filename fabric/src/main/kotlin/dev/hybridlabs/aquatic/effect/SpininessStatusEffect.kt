package dev.hybridlabs.aquatic.effect

import net.minecraft.tags.DamageTypeTags
import net.minecraft.world.damagesource.DamageTypes
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity

class SpininessMobEffect : MobEffect(MobEffectCategory.BENEFICIAL, 0x695672) {

    override fun isDurationEffectTick(duration: Int, amplifier: Int): Boolean {
        return duration % 40 == 0
    }

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int) {
        entity.lastDamageSource?.let { damageSource ->
            val attacker = damageSource.directEntity
            if (attacker is LivingEntity && !attacker.isDeadOrDying) {
                if (!damageSource.`is`(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) && !damageSource.`is`(DamageTypes.THORNS)) {
                    attacker.hurt(entity.damageSources().thorns(entity), 2.0f)
                }
            }
        }
    }
}
