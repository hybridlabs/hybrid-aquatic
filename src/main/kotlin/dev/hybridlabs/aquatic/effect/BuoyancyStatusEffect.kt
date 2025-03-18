package dev.hybridlabs.aquatic.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.math.Vec3d

class BuoyancyStatusEffect : StatusEffect(StatusEffectCategory.BENEFICIAL, 0xffe478) {

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        if (entity.isSubmergedInWater) {
            val upwardForce = 0.25 + (0.03 * amplifier)
            entity.velocity = Vec3d(entity.velocity.x, upwardForce, entity.velocity.z)
        }
    }
}
