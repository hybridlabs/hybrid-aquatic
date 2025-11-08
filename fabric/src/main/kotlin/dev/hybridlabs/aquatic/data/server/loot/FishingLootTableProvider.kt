package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
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
                HybridAquaticLootTables.HA_SMALL_FISH
            ),
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_SMALL_FISH)
                .pool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HybridAquaticItemTags.SMALL_FISH)
                                .setWeight(3)
                        )
                        .build()
                )
        )

        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
                HybridAquaticLootTables.HA_MEDIUM_FISH
            ),
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_MEDIUM_FISH)
                .pool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HybridAquaticItemTags.MEDIUM_FISH)
                                .setWeight(3)
                        )
                        .build()
                )
        )

        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
                HybridAquaticLootTables.HA_LARGE_FISH
            ),
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_LARGE_FISH)
                .pool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HybridAquaticItemTags.LARGE_FISH)
                                .setWeight(3)
                        )
                        .build()
                )
        )

        // fishing treasure loot table extension
        exporter.accept(
            ResourceKey.create(
                Registries.LOOT_TABLE,
                HybridAquaticLootTables.HA_CRATES
            ),
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_CRATES)
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.CRAB_POT.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.HYBRID_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.OAK_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.SPRUCE_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.BIRCH_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.DARK_OAK_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.ACACIA_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.JUNGLE_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.BAMBOO_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.MANGROVE_CRATE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.CHERRY_CRATE.get())).build()
                )
        )
    }
}