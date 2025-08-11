@file:Suppress("DEPRECATION")

package dev.hybridlabs.aquatic.loot

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object LootTableModifications {
    fun registerLootModifications() {
        LootTableEvents.MODIFY.register { id, tableBuilder, source ->
            if (source.isBuiltin) {
                when (id) {
                    LootTables.FISHING_FISH_GAMEPLAY -> {
                        tableBuilder.modifyPools { pool ->
                            pool.with(
                                LootTableEntry.builder(
                                    RegistryKey.of(RegistryKeys.LOOT_TABLE, HybridAquaticLootTables.FISHING_DEEP_SEA_FISH_ID))
                                    .weight(30)
                                    .quality(1)
                            )
                            pool.with(
                                LootTableEntry.builder(
                                    RegistryKey.of(RegistryKeys.LOOT_TABLE, HybridAquaticLootTables.FISHING_OPEN_OCEAN_FISH_ID)
                                )
                                    .weight(20)
                                    .quality(1)
                            )
                            pool.with(
                                LootTableEntry.builder(
                                    RegistryKey.of(RegistryKeys.LOOT_TABLE, HybridAquaticLootTables.FISHING_REEF_FISH_ID)
                                )
                                    .weight(20)
                                    .quality(1)
                            )
                            pool.with(
                                LootTableEntry.builder(
                                    RegistryKey.of(RegistryKeys.LOOT_TABLE, HybridAquaticLootTables.FISHING_TROPICAL_FRESHWATER_FISH_ID)
                                )
                                    .weight(20)
                                    .quality(1)
                            )
                            pool.with(
                                LootTableEntry.builder(
                                    RegistryKey.of(RegistryKeys.LOOT_TABLE, HybridAquaticLootTables.FISHING_TREASURE_ID)
                                )
                                    .weight(20)
                                    .quality(1)
                            )
                        }
                    }
                }
            }
        }
    }
}
