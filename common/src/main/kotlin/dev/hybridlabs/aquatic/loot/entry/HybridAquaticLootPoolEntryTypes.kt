package dev.hybridlabs.aquatic.loot.entry

import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType

object HybridAquaticLootPoolEntryTypes {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItemEntry.CODEC)

    private fun register(
        id: String,
        serializer: MapCodec<out LootPoolEntryContainer>
    ): RegistryObject<LootPoolEntryType> {
        return CommonClass.LOOT_POOL_ENTRY_TYPE.register(
            id
        ) { LootPoolEntryType(serializer) }
    }
}
