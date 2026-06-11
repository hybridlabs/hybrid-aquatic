package dev.hybridlabs.aquatic.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.phys.Vec3

class BuoyancyMobEffect : MobEffect(MobEffectCategory.BENEFICIAL, 0xffe478) {

    override fun shouldApplyEffectTickThisTick(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int): Boolean {
        if (entity.isUnderWater) {
            val upwardForce = 0.25 + (0.03 * amplifier)
            entity.deltaMovement = Vec3(entity.deltaMovement.x, upwardForce, entity.deltaMovement.z)
            return true
        }
        return false
    }
}