package dev.hybridlabs.aquatic.enchantment

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object HybridAquaticEnchantments {
    val LIVECATCH = register("live_catch")

    private fun register(id: String): RegistryKey<Enchantment> {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(HybridAquatic.MOD_ID, id))
    }
}
