package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.MESSAGE_KEY
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.VARIANT_KEY
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.item.BlockItem.BLOCK_ENTITY_TAG_KEY
import net.minecraft.item.Items
import net.minecraft.item.WrittenBookItem.*
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
            LootTable.builder().pool(
                LootPool.builder()
                    .with(ItemEntry.builder(block))
                    .conditionally(WITH_SILK_TOUCH_OR_SHEARS)
            )
        }

        //region wood

        addDrop(HybridAquaticBlocks.DRIFTWOOD_LOG)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_WOOD)
        addDrop(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG)
        addDrop(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_PLANKS)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_STAIRS)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_BUTTON)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_FENCE)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE)
        addDrop(HybridAquaticBlocks.DRIFTWOOD_SLAB, slabDrops(HybridAquaticBlocks.DRIFTWOOD_SLAB))
        addDrop(HybridAquaticBlocks.DRIFTWOOD_DOOR, doorDrops(HybridAquaticBlocks.DRIFTWOOD_DOOR))

        //endregion


        // living sponge
        addDrop(HybridAquaticBlocks.TUBE_SPONGE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        ItemEntry.builder(block).conditionally(WITH_SILK_TOUCH),
                        LootTableEntry.builder(HybridAquaticLootTables.TUBE_SPONGE_LOOT_ID)
                    )
                )
            )
        }

        // hydrothermal vent
        addDrop(HybridAquaticBlocks.HYDROTHERMAL_VENT) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        ItemEntry.builder(block).conditionally(WITH_SILK_TOUCH),
                        LootTableEntry.builder(HybridAquaticLootTables.HYDROTHERMAL_VENT_LOOT_ID)
                    )
                )
            )
        }

        // message in a bottle
        addDrop(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE) { block ->
            LootTable.builder().pool(
                    LootPool.builder().with(
                            AlternativeEntry.builder(
                                ItemEntry.builder(block).conditionally(WITH_SILK_TOUCH).apply(
                                    CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                        .withOperation(VARIANT_KEY, "$BLOCK_ENTITY_TAG_KEY.$VARIANT_KEY")
                                        .withOperation(MESSAGE_KEY, "$BLOCK_ENTITY_TAG_KEY.$MESSAGE_KEY")
                                ),
                                ItemEntry.builder(Items.WRITTEN_BOOK).apply(
                                    CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                        .withOperation("$MESSAGE_KEY.tag.$PAGES_KEY", PAGES_KEY)
                                        .withOperation("$MESSAGE_KEY.tag.$TITLE_KEY", TITLE_KEY)
                                        .withOperation("$MESSAGE_KEY.tag.$AUTHOR_KEY", AUTHOR_KEY)
                                )
                            )
                        )
                )
        }

        // crate
        addDrop(HybridAquaticBlocks.HYBRID_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.DRIFTWOOD_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.DRIFTWOOD_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.OAK_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.OAK_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.BIRCH_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.SPRUCE_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.DARK_OAK_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.ACACIA_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.JUNGLE_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.MANGROVE_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        addDrop(HybridAquaticBlocks.CHERRY_CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID).conditionally(
                            MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.AXES))
                        ),
                        ItemEntry.builder(block),
                    )
                )
            )
        }

        // generate remaining drops
        Registries.BLOCK
            .filter(filterHybridAquatic(Registries.BLOCK))
            .filter { block -> block.lootTableId !in lootTables }
            .forEach(::addDrop)
    }
}
