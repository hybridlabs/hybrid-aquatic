package dev.hybridlabs.aquatic.loot_table

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootTables
import net.minecraft.loot.condition.EntityPropertiesLootCondition
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.predicate.entity.FishingHookPredicate
import net.minecraft.util.Identifier

object LootTableModifications {
    private val fishLootTable = Identifier(HybridAquatic.MOD_ID, "gameplay/fishing/fish")
    private val treasureLootTable = Identifier(HybridAquatic.MOD_ID, "gameplay/fishing/treasure")

    init {
        LootTableEvents.MODIFY.register { resourceManager, lootManager, id, tableBuilder, source ->
            if (source.isBuiltin) {
                when(id) {
                    LootTables.FISHING_FISH_GAMEPLAY -> {
                        tableBuilder.modifyPools { defaultPools ->
                            defaultPools
                                .with(LootTableEntry.builder(fishLootTable).weight(85).quality(-1))
                                .with(LootTableEntry.builder(treasureLootTable).weight(5).quality(2).conditionally(EntityPropertiesLootCondition.builder(
                                    LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().typeSpecific(FishingHookPredicate.of(true))
                                )))
                        }
                    }
                }
            }
        }
    }
}