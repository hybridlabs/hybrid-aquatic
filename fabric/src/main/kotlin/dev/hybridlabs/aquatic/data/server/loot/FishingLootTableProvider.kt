package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.loot.HALootTables
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.TagEntry
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class FishingLootTableProvider(output: FabricDataOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(output, lookupProvider, LootContextParamSets.FISHING) {
    override fun generate(exporter: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
            HALootTables.HA_SMALL_FISH),
            LootTable.lootTable()
                .setRandomSequence(HALootTables.HA_SMALL_FISH)
                .pool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HAItemTags.SMALL_FISH)
                                .setWeight(3)
                        )
                        .build()
                )
        )

        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
            HALootTables.HA_MEDIUM_FISH),
            LootTable.lootTable()
                .setRandomSequence(HALootTables.HA_MEDIUM_FISH)
                .pool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HAItemTags.MEDIUM_FISH)
                                .setWeight(3)
                        )
                        .build()
                )
        )

        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
            HALootTables.HA_LARGE_FISH),
            LootTable.lootTable()
                .setRandomSequence(HALootTables.HA_LARGE_FISH)
                .pool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HAItemTags.LARGE_FISH)
                                .setWeight(3)
                        )
                        .build()
                )
        )

        // fishing treasure loot table extension
        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
            HALootTables.HA_CRATES),
            LootTable.lootTable()
                .setRandomSequence(HALootTables.HA_CRATES)
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.CRAB_POT.get()))
                        .add(LootItem.lootTableItem(HAItems.HYBRID_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.OAK_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.SPRUCE_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.BIRCH_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.DARK_OAK_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.ACACIA_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.JUNGLE_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.BAMBOO_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.MANGROVE_CRATE.get()))
                        .add(LootItem.lootTableItem(HAItems.CHERRY_CRATE.get())).build()
                    //.add(MessageInABottleLootItem.lootTableItem())
                )
        )
    }
}