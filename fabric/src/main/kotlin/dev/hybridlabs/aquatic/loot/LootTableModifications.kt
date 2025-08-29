package dev.hybridlabs.aquatic.loot

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.FishingHookPredicate
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.entries.LootTableReference
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition

object LootTableModifications {
    fun registerLootModifications() {
        LootTableEvents.MODIFY.register { _, _, id, tableBuilder, source ->
            if (source.isBuiltin) {
                when (id) {
                    // modify fishing loot table
                    BuiltInLootTables.FISHING_FISH -> {
                        tableBuilder.modifyPools { defaultPools ->
                            defaultPools
                                // add fishing loot tables
                                .add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.FISHING_DEEP_SEA_FISH_ID)
                                        .setWeight(30)
                                        .setQuality(1)
                                ).add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.FISHING_OPEN_OCEAN_FISH_ID)
                                        .setWeight(40)
                                        .setQuality(1)
                                ).add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.FISHING_REEF_FISH_ID)
                                        .setWeight(50)
                                        .setQuality(-1)
                                ).add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.FISHING_TROPICAL_FRESHWATER_FISH_ID)
                                        .setWeight(30)
                                        .setQuality(1)
                                )
                                // add fishing treasure loot table
                                .add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.FISHING_TREASURE_ID)
                                        .setWeight(10)
                                        .setQuality(2)
                                        .`when`(
                                            LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity()
                                                    .subPredicate(FishingHookPredicate.inOpenWater(true))
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
