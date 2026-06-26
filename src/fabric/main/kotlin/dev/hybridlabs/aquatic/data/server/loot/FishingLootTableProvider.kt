package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.TagEntry
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import java.util.function.BiConsumer

class FishingLootTableProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextParamSets.FISHING) {
    override fun generate(exporter: BiConsumer<ResourceLocation, LootTable.Builder>) {
        exporter.accept(
            HybridAquaticLootTables.HA_SMALL_FISH,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_SMALL_FISH)
                .withPool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HybridAquaticItemTags.SMALL_FISH)
                                .setWeight(3)
                        )

                )
        )

        exporter.accept(
            HybridAquaticLootTables.HA_MEDIUM_FISH,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_MEDIUM_FISH)
                .withPool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HybridAquaticItemTags.MEDIUM_FISH)
                                .setWeight(3)
                        )

                )
        )

        exporter.accept(
            HybridAquaticLootTables.HA_LARGE_FISH,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_LARGE_FISH)
                .withPool(
                    LootPool.lootPool()
                        .add(
                            TagEntry.expandTag(HybridAquaticItemTags.LARGE_FISH)
                                .setWeight(3)
                        )

                )
        )

        // fishing treasure loot table extension
        exporter.accept(
            HybridAquaticLootTables.HA_CRATES,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HA_CRATES)
                .withPool(
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
                        .add(LootItem.lootTableItem(HybridAquaticItems.CHERRY_CRATE.get()))
                    //.add(MessageInABottleLootItem.lootTableItem())
                )
        )
    }
}