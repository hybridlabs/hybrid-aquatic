package dev.hybridlabs.aquatic.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity

class CorrosionMobEffect : MobEffect(MobEffectCategory.HARMFUL, 0x9d9136) {

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int): Boolean {
        if (entity.level().isClientSide) return false
        val damage = amplifier + 1
        corrodeTool(entity, damage)
        corrodeArmor(entity, damage)
        return true
    }

    override fun shouldApplyEffectTickThisTick(duration: Int, amplifier: Int): Boolean {
        return duration % 20 == 0
    }

    private fun corrodeTool(entity: LivingEntity, damage: Int) {
        val mainHandStack = entity.mainHandItem
        if (mainHandStack.isDamageableItem) {
            mainHandStack.hurtAndBreak(damage, entity, entity.getEquipmentSlotForItem(mainHandStack))
        }

        val offHandStack = entity.offhandItem
        if (offHandStack.isDamageableItem) {
            mainHandStack.hurtAndBreak(1, entity, entity.getEquipmentSlotForItem(mainHandStack))
        }
    }

    private fun corrodeArmor(entity: LivingEntity, damage: Int) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = entity.getItemBySlot(slot)
            if (armorStack.isDamageableItem) {
                armorStack.hurtAndBreak(damage, entity, entity.getEquipmentSlotForItem(armorStack))
            }
        }
    }
}