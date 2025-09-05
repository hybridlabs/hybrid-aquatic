package dev.hybridlabs.aquatic.loot.entry

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType

object HybridAquaticLootPoolEntryTypes {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItemEntry.Serializer())

    private fun register(id: String, serializer: MessageInABottleItemEntry.Serializer): LootPoolEntryType {
        return Registry.register(
            BuiltInRegistries.LOOT_POOL_ENTRY_TYPE,
            ResourceLocation(Constants.MOD_ID, id), LootPoolEntryType(serializer)
        )
    }
}
