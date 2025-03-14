package dev.hybridlabs.aquatic.loot

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootTables
import net.minecraft.loot.condition.EntityPropertiesLootCondition
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.predicate.entity.FishingHookPredicate

object LootTableModifications {
    fun registerLootModifications() {
        LootTableEvents.MODIFY.register { _, _, id, tableBuilder, source ->
            if (source.isBuiltin) {
                when (id) {
                    // modify fishing loot table
                    LootTables.FISHING_FISH_GAMEPLAY -> {
                        tableBuilder.modifyPools { defaultPools ->
                            defaultPools
                                // add fishing loot tables
                                .with(
                                    LootTableEntry.builder(HybridAquaticLootTables.FISHING_DEEP_SEA_FISH_ID)
                                        .weight(30)
                                        .quality(1)
                                ).with(
                                    LootTableEntry.builder(HybridAquaticLootTables.FISHING_OPEN_OCEAN_FISH_ID)
                                        .weight(40)
                                        .quality(1)
                                ).with(
                                    LootTableEntry.builder(HybridAquaticLootTables.FISHING_REEF_FISH_ID)
                                        .weight(50)
                                        .quality(-1)
                                ).with(
                                    LootTableEntry.builder(HybridAquaticLootTables.FISHING_TROPICAL_FRESHWATER_FISH_ID)
                                        .weight(30)
                                        .quality(1)
                                )
                                // add fishing treasure loot table
                                .with(
                                    LootTableEntry.builder(HybridAquaticLootTables.FISHING_TREASURE_ID)
                                        .weight(10)
                                        .quality(2)
                                        .conditionally(
                                            EntityPropertiesLootCondition.builder(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.create()
                                                    .typeSpecific(FishingHookPredicate.of(true))
                                            )
                                        )
                                )
                        }
                    }
                }
            }
        }
    }
}
