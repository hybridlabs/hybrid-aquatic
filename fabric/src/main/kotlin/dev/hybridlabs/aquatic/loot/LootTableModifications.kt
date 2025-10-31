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
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.HA_SMALL_FISH)
                                        .setWeight(30)
                                        .setQuality(1)
                                )
                                .add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.HA_MEDIUM_FISH)
                                        .setWeight(25)
                                        .setQuality(1)
                                )
                                .add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.HA_LARGE_FISH)
                                        .setWeight(20)
                                        .setQuality(1)
                                )

                                // add fishing treasure loot table
                                .add(
                                    LootTableReference.lootTableReference(HybridAquaticLootTables.HA_CRATES)
                                        .setWeight(15)
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