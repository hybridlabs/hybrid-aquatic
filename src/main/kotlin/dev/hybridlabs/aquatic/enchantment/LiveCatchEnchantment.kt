package dev.hybridlabs.aquatic.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot

class LiveCatchEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.FISHING_ROD, arrayOf(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)) {
    override fun getMinPower(level: Int): Int {
        return 25
    }

    override fun getMaxLevel(): Int {
        return 1
    }
}