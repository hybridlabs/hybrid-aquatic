package dev.hybridlabs.aquatic.enchantment

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object HybridAquaticEnchantments {
    val LIVECATCH = register("live_catch", LiveCatchEnchantment())

    private fun register(id: String, enchantment: Enchantment): Enchantment {
        return Registry.register(Registry.ENCHANTMENT, Identifier(HybridAquatic.MOD_ID, id), enchantment)
    }
}