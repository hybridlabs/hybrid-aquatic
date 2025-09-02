package dev.hybridlabs.aquatic.enchantment

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.item.enchantment.Enchantment

object HybridAquaticEnchantments {
    val LIVECATCH = register("live_catch", LiveCatchEnchantment())

    private fun register(id: String, enchantment: Enchantment): RegistryObject<Enchantment> {
        return CommonClass.ENCHANTMENTS.register(id) { enchantment }
    }
}