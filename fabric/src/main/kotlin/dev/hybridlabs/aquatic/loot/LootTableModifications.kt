package dev.hybridlabs.aquatic.loot

import dev.hybridlabs.aquatic.config.HAConfigHolder
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.advancements.predicates.entity.EntityPredicate
import net.minecraft.advancements.predicates.entity.FishingHookPredicate
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition

object LootTableModifications {
    fun registerLootModifications() {

        val configHandler = HAConfigHolder.handler

        if (!configHandler.config.addFishingLoot) return

        LootTableEvents.MODIFY.register { key, tableBuilder, source, registries ->
            if (source.isBuiltin) {
                when (key) {
                    // modify fishing loot table
                    BuiltInLootTables.FISHING_FISH -> {
                        tableBuilder.modifyPools { defaultPools ->
                            defaultPools
                                // add fishing loot tables
                                .add(
                                    NestedLootTable.lootTableReference(registries.lookupOrThrow(Registries.LOOT_TABLE).getOrThrow(ResourceKey.create(Registries.LOOT_TABLE, HALootTables.HA_SMALL_FISH)))
                                        .setWeight(30)
                                        .setQuality(1)
                                )
                                .add(
                                    NestedLootTable.lootTableReference(registries.lookupOrThrow(Registries.LOOT_TABLE).getOrThrow(ResourceKey.create(Registries.LOOT_TABLE, HALootTables.HA_MEDIUM_FISH)))
                                        .setWeight(25)
                                        .setQuality(1)
                                )
                                .add(
                                    NestedLootTable.lootTableReference(registries.lookupOrThrow(Registries.LOOT_TABLE).getOrThrow(ResourceKey.create(Registries.LOOT_TABLE, HALootTables.HA_LARGE_FISH)))
                                        .setWeight(20)
                                        .setQuality(1)
                                )

                                // add fishing treasure loot table
                                .add(
                                    NestedLootTable.lootTableReference(registries.lookupOrThrow(Registries.LOOT_TABLE).getOrThrow(ResourceKey.create(Registries.LOOT_TABLE, HALootTables.HA_CRATES)))
                                        .setWeight(15)
                                        .setQuality(2)
                                        .`when`(
                                            LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity()
                                                    .put(
                                                        FishingHookPredicate.CODEC,
                                                        FishingHookPredicate.inOpenWater(true)
                                                    )
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