package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HAAquaticItems
import dev.hybridlabs.aquatic.loot.HALootTables
import dev.hybridlabs.aquatic.tag.HAItemTags
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
            HALootTables.HA_SMALL_FISH,
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
            HALootTables.HA_MEDIUM_FISH,
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
            HALootTables.HA_LARGE_FISH,
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
            HALootTables.HA_CRATES,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.HA_CRATES)
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAAquaticItems.CRAB_POT.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.HYBRID_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.OAK_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.SPRUCE_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.BIRCH_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.DARK_OAK_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.ACACIA_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.JUNGLE_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.BAMBOO_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.MANGROVE_CRATE.get()))
                        .add(LootItem.lootTableItem(HAAquaticItems.CHERRY_CRATE.get())).build()
                    //.add(MessageInABottleLootItem.lootTableItem())
                )
        )
    }
}