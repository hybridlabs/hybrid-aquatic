package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import java.util.concurrent.CompletableFuture

class EnchantmentProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        val itemLookup = registries.getWrapperOrThrow(RegistryKeys.ITEM)
        entries.add(HybridAquaticEnchantments.LIVECATCH,
            Enchantment.builder(
                Enchantment.definition(
                    itemLookup.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                    2,
                    1,
                    Enchantment.constantCost(25),
                    Enchantment.leveledCost(55, 8),
                    4,
                    AttributeModifierSlot.HAND
                )
            )
        )
    }

    fun Entries.add(key: RegistryKey<Enchantment>, builder: Enchantment.Builder) {
        add(key, builder.build(key.value))
    }

    override fun getName(): String {
        return "Enchantment Provider"
    }
}