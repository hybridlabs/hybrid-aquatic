package dev.hybridlabs.aquatic.enchantment

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticEnchantments {
    val LIVECATCH = register("live_catch", LiveCatchEnchantment())

    private fun register(id: String, enchantment: Enchantment): Enchantment {
        return Registry.register(Registries.ENCHANTMENT, Identifier(HybridAquatic.MOD_ID, id), enchantment)
    }
}