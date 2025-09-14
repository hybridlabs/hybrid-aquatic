package dev.hybridlabs.aquatic.loot.entry

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType

object HybridAquaticLootPoolEntryTypes {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItemEntry.Serializer())

    private fun register(
        id: String,
        serializer: MessageInABottleItemEntry.Serializer
    ): RegistryObject<LootPoolEntryType> {
        return CommonClass.LOOT_POOL_ENTRY_TYPE.register(
            id
        ) { LootPoolEntryType(serializer) }
    }
}
