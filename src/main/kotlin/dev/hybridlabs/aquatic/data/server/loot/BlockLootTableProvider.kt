package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.item.BlockItem
import net.minecraft.item.Items
import net.minecraft.item.WrittenBookItem
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.MatchToolLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.CopyNbtLootFunction
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.registry.Registries
import net.minecraft.registry.tag.ItemTags

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        // anemone
        addDrop(HybridAquaticBlocks.ANEMONE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(block))
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS)
                )
        }

        // message in a bottle
        addDrop(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                ItemEntry.builder(block).conditionally(WITH_SILK_TOUCH)
                                    .apply(
                                        CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                            .withOperation(MessageInABottleBlockEntity.VARIANT_KEY, BlockItem.BLOCK_ENTITY_TAG_KEY + "." + MessageInABottleBlockEntity.VARIANT_KEY)
                                            .withOperation(MessageInABottleBlockEntity.MESSAGE_KEY, BlockItem.BLOCK_ENTITY_TAG_KEY + "." + MessageInABottleBlockEntity.MESSAGE_KEY)
                                    ),
                                ItemEntry.builder(Items.WRITTEN_BOOK)
                                    .apply(
                                        CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                            .withOperation(MessageInABottleBlockEntity.MESSAGE_KEY + ".tag." + WrittenBookItem.PAGES_KEY, WrittenBookItem.PAGES_KEY)
                                            .withOperation(MessageInABottleBlockEntity.MESSAGE_KEY + ".tag." + WrittenBookItem.TITLE_KEY, WrittenBookItem.TITLE_KEY)
                                            .withOperation(MessageInABottleBlockEntity.MESSAGE_KEY + ".tag." + WrittenBookItem.AUTHOR_KEY, WrittenBookItem.AUTHOR_KEY)
                                    )
                            )
                        )
                )
        }

        // crate
        addDrop(HybridAquaticBlocks.CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.CRATE_TREASURE_ID)
                            .conditionally(
                                MatchToolLootCondition.builder(
                                    ItemPredicate.Builder.create()
                                        .tag(ItemTags.AXES)
                                )
                            ),
                        ItemEntry.builder(block),
                    )
                ))
        }

        // generate remaining drops
        Registries.BLOCK
            .filter(filterHybridAquatic(Registries.BLOCK))
            .filter { block -> block.lootTableId !in lootTables }
            .forEach(::addDrop)
    }
}
