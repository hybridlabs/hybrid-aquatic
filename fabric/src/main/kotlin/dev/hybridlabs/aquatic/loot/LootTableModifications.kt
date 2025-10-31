package dev.hybridlabs.aquatic.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.FishingHookPredicate
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EntityType.ELDER_GUARDIAN
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition

object LootTableModifications {
    fun registerLootModifications() {
        LootTableEvents.MODIFY.register { key, tableBuilder, source, registries ->
            if (source.isBuiltin) {
                when (key) {
                    // modify fishing loot table
                    BuiltInLootTables.FISHING_FISH -> {
                        tableBuilder.modifyPools { defaultPools ->
                            defaultPools
                                // add fishing loot tables
                                .add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                        HybridAquaticLootTables.HA_SMALL_FISH))
                                        .setWeight(30)
                                        .setQuality(1)
                                )
                                .add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                        HybridAquaticLootTables.HA_MEDIUM_FISH))
                                        .setWeight(25)
                                        .setQuality(1)
                                )
                                .add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                        HybridAquaticLootTables.HA_LARGE_FISH))
                                        .setWeight(20)
                                        .setQuality(1)
                                )

                                // add fishing treasure loot table
                                .add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                        HybridAquaticLootTables.HA_CRATES))
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