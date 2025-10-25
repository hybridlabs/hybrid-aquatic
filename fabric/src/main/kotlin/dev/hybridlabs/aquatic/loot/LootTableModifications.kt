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
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator

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
                                            HybridAquaticLootTables.FISHING_DEEP_SEA_FISH_ID))
                                        .setWeight(30)
                                        .setQuality(1)
                                ).add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                            HybridAquaticLootTables.FISHING_OPEN_OCEAN_FISH_ID))
                                        .setWeight(40)
                                        .setQuality(1)
                                ).add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                        HybridAquaticLootTables.FISHING_REEF_FISH_ID))
                                        .setWeight(50)
                                        .setQuality(-1)
                                ).add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                        HybridAquaticLootTables.FISHING_TROPICAL_FRESHWATER_FISH_ID))
                                        .setWeight(30)
                                        .setQuality(1)
                                )
                                // add fishing treasure loot table
                                .add(
                                    NestedLootTable.lootTableReference( ResourceKey.create(Registries.LOOT_TABLE,
                                        HybridAquaticLootTables.FISHING_TREASURE_ID))
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