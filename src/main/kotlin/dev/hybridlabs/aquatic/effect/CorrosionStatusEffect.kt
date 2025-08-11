package dev.hybridlabs.aquatic.effect

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class CorrosionStatusEffect : StatusEffect(StatusEffectCategory.HARMFUL, 0x9d9136) {

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int): Boolean {
        if (entity.world.isClient)
        corrodeTool(entity)
        corrodeHelmet(entity)
        corrodeChestplate(entity)
        corrodeLeggings(entity)
        corrodeBoots(entity)
    return true
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 20 == 0
    }

    private fun corrodeTool(entity: LivingEntity) {
        val mainHandStack = entity.mainHandStack
        if (mainHandStack.isDamageable) {
            mainHandStack.damage(1, entity, EquipmentSlot.MAINHAND)
        }

        val offHandStack = entity.offHandStack
        if (offHandStack.isDamageable) {
            mainHandStack.damage(1, entity, EquipmentSlot.MAINHAND)
        }
    }

    private fun corrodeHelmet(entity: LivingEntity) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = entity.getEquippedStack(slot)
            if (armorStack.isDamageable) {
                armorStack.damage(1, entity, EquipmentSlot.HEAD)
            }
        }
    }

    private fun corrodeChestplate(entity: LivingEntity) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = entity.getEquippedStack(slot)
            if (armorStack.isDamageable) {
                armorStack.damage(1, entity, EquipmentSlot.CHEST)
            }
        }
    }

    private fun corrodeLeggings(entity: LivingEntity) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = entity.getEquippedStack(slot)
            if (armorStack.isDamageable) {
                armorStack.damage(1, entity, EquipmentSlot.LEGS)
            }
        }
    }

    private fun corrodeBoots(entity: LivingEntity) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = entity.getEquippedStack(slot)
            if (armorStack.isDamageable) {
                armorStack.damage(1, entity, EquipmentSlot.FEET)
            }
        }
    }
}