package dev.hybridlabs.aquatic.loot.entry

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.loot.entry.LootPoolEntry
import net.minecraft.loot.entry.LootPoolEntryType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.JsonSerializer

object HybridAquaticLootPoolEntryTypes {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItemEntry.Serializer())

    private fun register(id: String, serializer: JsonSerializer<out LootPoolEntry>): LootPoolEntryType {
        return Registry.register(Registries.LOOT_POOL_ENTRY_TYPE, Identifier(HybridAquatic.MOD_ID, id), LootPoolEntryType(serializer))
    }
}
