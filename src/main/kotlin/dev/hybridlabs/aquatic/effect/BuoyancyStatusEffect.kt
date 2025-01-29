package dev.hybridlabs.aquatic.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d

class BuoyancyStatusEffect : StatusEffect(StatusEffectCategory.BENEFICIAL, 0xffe478) {

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 20 == 0
    }

    override fun applyUpdateEffect(world: ServerWorld, entity: LivingEntity, amplifier: Int): Boolean {
        val currentVelocity = entity.velocity
        if (entity.isSubmergedInWater) {
            entity.velocity = Vec3d(currentVelocity.x, 1.0, currentVelocity.z)
        }

        return true
    }
}
