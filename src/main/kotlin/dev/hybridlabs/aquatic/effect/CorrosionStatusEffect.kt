package dev.hybridlabs.aquatic.effect

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class CorrosionStatusEffect : StatusEffect(StatusEffectCategory.HARMFUL, 0x9d9136) {

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        if (entity.world.isClient) return
        corrodeTool(entity)
        corrodeArmor(entity)
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 20 == 0
    }

    private fun corrodeTool(entity: LivingEntity) {
        val mainHandStack = entity.mainHandStack
        if (mainHandStack.isDamageable) {
            mainHandStack.damage(1, entity) { it.sendToolBreakStatus(entity.activeHand) }
        }

        val offHandStack = entity.offHandStack
        if (offHandStack.isDamageable) {
            offHandStack.damage(1, entity) { it.sendToolBreakStatus(entity.activeHand) }
        }
    }

    private fun corrodeArmor(entity: LivingEntity) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = entity.getEquippedStack(slot)
            if (armorStack.isDamageable) {
                armorStack.damage(1, entity) { it.sendEquipmentBreakStatus(slot) }
            }
        }
    }
}