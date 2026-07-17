package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.loot.HALootTables
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.decoration.Painting
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.TagEntry
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider
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

        // message in a bottle loot table
        // TODO: Actually check if variant is in HAPaintingTags.KEEPS_PAINTING_VARIANT.
        //  Couldn't find a way to do it but we don't need this right now
        val messageInABottleLootPoolBuilder = LootPool.lootPool()
        BuiltInRegistries.PAINTING_VARIANT.forEach { variant ->
            val variantKey = BuiltInRegistries.PAINTING_VARIANT.getKey(variant)
            if (variantKey.namespace != Constants.MOD_ID) return@forEach

            messageInABottleLootPoolBuilder.add(LootItem.lootTableItem(Items.PAINTING).apply(
                CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                    .copy("$variantKey", Painting.VARIANT_TAG)
            ))
        }

        exporter.accept(
            HALootTables.MESSAGE_IN_A_BOTTLE,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.MESSAGE_IN_A_BOTTLE)
                .pool(messageInABottleLootPoolBuilder.build())
        )
    }
}