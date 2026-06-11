package dev.hybridlabs.aquatic.loot

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.config.ConfigHelper
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.FishingHookPredicate
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.entries.LootTableReference
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition
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

object LootTableModifications {
    fun registerLootModifications() {

        val configHandler = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)

        if (!configHandler.config.addFishingLoot) return

        LootTableEvents.MODIFY.register { _, _, id, tableBuilder, source ->
        LootTableEvents.MODIFY.register { key, tableBuilder, source, registries ->
            if (source.isBuiltin) {
                when (key) {
                    // modify fishing loot table
                    BuiltInLootTables.FISHING_FISH -> {
                        tableBuilder.modifyPools { defaultPools ->
                            defaultPools
                                // add fishing loot tables
                                .add(
                                    LootTableReference.lootTableReference(HALootTables.HA_SMALL_FISH)
                                        .setWeight(30)
                                        .setQuality(1)
                                )
                                .add(
                                    LootTableReference.lootTableReference(HALootTables.HA_MEDIUM_FISH)
                                        .setWeight(25)
                                        .setQuality(1)
                                )
                                .add(
                                    LootTableReference.lootTableReference(HALootTables.HA_LARGE_FISH)
                                        .setWeight(20)
                                        .setQuality(1)
                                )

                                // add fishing treasure loot table
                                .add(
                                    LootTableReference.lootTableReference(HALootTables.HA_CRATES)
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