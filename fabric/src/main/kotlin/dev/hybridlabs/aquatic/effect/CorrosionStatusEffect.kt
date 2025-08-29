package dev.hybridlabs.aquatic.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity

class CorrosionMobEffect : MobEffect(MobEffectCategory.HARMFUL, 0x9d9136) {

    override fun applyEffectTick(entity: LivingEntity, amplifier: Int) {
        if (entity.level().isClientSide) return
        corrodeTool(entity)
        corrodeArmor(entity)
    }

    override fun isDurationEffectTick(duration: Int, amplifier: Int): Boolean {
        return duration % 20 == 0
    }

    private fun corrodeTool(entity: LivingEntity) {
        val mainHandStack = entity.mainHandItem
        if (mainHandStack.isDamageableItem) {
            mainHandStack.hurtAndBreak(1, entity) { it.broadcastBreakEvent(entity.usedItemHand) }
        }

        val offHandStack = entity.offhandItem
        if (offHandStack.isDamageableItem) {
            offHandStack.hurtAndBreak(1, entity) { it.broadcastBreakEvent(entity.usedItemHand) }
        }
    }

    private fun corrodeArmor(entity: LivingEntity) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = entity.getItemBySlot(slot)
            if (armorStack.isDamageableItem) {
                armorStack.hurtAndBreak(1, entity) { it.broadcastBreakEvent(slot) }
            }
        }
    }
}