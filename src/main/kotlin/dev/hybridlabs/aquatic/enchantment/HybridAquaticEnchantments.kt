package dev.hybridlabs.aquatic.enchantment

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier

object HybridAquaticEnchantments {
    val LIVECATCH = register("live_catch", LiveCatchEnchantment(Enchantment.properties(ItemTags.FISHING_ENCHANTABLE, 2, 1, Enchantment.constantCost(25), Enchantment.constantCost(30), 10, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)))

    private fun register(id: String, enchantment: Enchantment): Enchantment {
        return Registry.register(Registries.ENCHANTMENT, Identifier(HybridAquatic.MOD_ID, id), enchantment)
    }
}
