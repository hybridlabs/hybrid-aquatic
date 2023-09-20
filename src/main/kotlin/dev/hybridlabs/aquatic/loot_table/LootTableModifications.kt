package dev.hybridlabs.aquatic.loot_table

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.util.Identifier

object LootTableModifications {
    private val fishLootTable = Identifier(HybridAquatic.MOD_ID, "gameplay/fishing/fish")

    init {
        LootTableEvents.MODIFY.register { resourceManager, lootManager, id, tableBuilder, source ->
            if (source.isBuiltin) {
                when(id) {
                    LootTables.FISHING_FISH_GAMEPLAY -> {
                        tableBuilder.modifyPools { defaultPools -> defaultPools.with(LootTableEntry.builder(fishLootTable).weight(85).quality(-1)) }
                    }
                }
            }
        }
    }
}