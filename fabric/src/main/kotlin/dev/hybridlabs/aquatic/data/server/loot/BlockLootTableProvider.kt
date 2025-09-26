package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.MESSAGE_KEY
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.VARIANT_KEY
import dev.hybridlabs.aquatic.block.wood.HybridAquaticWoodBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.SeaMessageBookItem.Companion.SEA_MESSAGE_KEY
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.BlockItem.BLOCK_ENTITY_TAG
import net.minecraft.world.level.block.BaseCoralWallFanBlock
import net.minecraft.world.level.block.WallTorchBlock
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.LootTableReference
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.MatchTool
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        // anemone
        add(HybridAquaticBlocks.ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block)).conditionally(HAS_SHEARS_OR_SILK_TOUCH.build()).build()
            )
        }

        add(HybridAquaticBlocks.STRAWBERRY_ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(HAS_SHEARS_OR_SILK_TOUCH.build()).build()
            )
        }

        add(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(HAS_SHEARS_OR_SILK_TOUCH.build()).build()
            )
        }

        add(HybridAquaticBlocks.RED_ALGAE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(HAS_SHEARS_OR_SILK_TOUCH.build()).build()
            )
        }

        add(HybridAquaticBlocks.SEA_LETTUCE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(HAS_SHEARS_OR_SILK_TOUCH.build()).build()
            )
        }

        add(HybridAquaticBlocks.TUBE_WORM.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .add(
                        (2..4).fold(LootItem.lootTableItem(block)) { item, worms ->
                            item.apply(
                                SetItemCountFunction.setCount(ConstantValue.exactly(worms.toFloat()))
                                    .`when`(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                            .setProperties(
                                                StatePropertiesPredicate.Builder.properties()
                                                    .hasProperty(TubeWormBlock.WORMS, worms)
                                            )
                                    )
                            )
                        }
                    )
            )
        }

        add(HybridAquaticBlocks.FLOATING_SARGASSUM.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(HAS_SHEARS_OR_SILK_TOUCH.build()).build()
            )
        }

        add(HybridAquaticBlocks.SARGASSUM.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .build()
            )
        }

        add(HybridAquaticBlocks.BULL_KELP.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .build()
            )
        }

        //region wood
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_LOG.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_WOOD.get())
        createSingleItemTable(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_LOG.get())
        createSingleItemTable(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_WOOD.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_PLANKS.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_STAIRS.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_TRAPDOOR.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_PRESSURE_PLATE.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_BUTTON.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE.get())
        createSingleItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE_GATE.get())
        add(
            HybridAquaticWoodBlocks.DRIFTWOOD_SLAB.get(),
            createSlabItemTable(HybridAquaticWoodBlocks.DRIFTWOOD_SLAB.get())
        )
        add(HybridAquaticWoodBlocks.DRIFTWOOD_DOOR.get(), createDoorTable(HybridAquaticWoodBlocks.DRIFTWOOD_DOOR.get()))

        createSingleItemTable(HybridAquaticBlocks.GLOWSTICK.get())

        //endregion

        //region corals
        createSilkTouchOnlyTable(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.LOPHELIA_CORAL.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.THORN_CORAL_BLOCK.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.THORN_CORAL.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_THORN_CORAL.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.THORN_CORAL_FAN.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_THORN_CORAL_FAN.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.SUN_CORAL_BLOCK.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.SUN_CORAL.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_SUN_CORAL.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.SUN_CORAL_FAN.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_SUN_CORAL_FAN.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.BUTTON_CORAL.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_BUTTON_CORAL.get())

        createSilkTouchOnlyTable(HybridAquaticBlocks.BUTTON_CORAL_FAN.get())
        createSilkTouchOnlyTable(HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN.get())

        //endregion

        // thermal vents
        add(HybridAquaticBlocks.THERMAL_VENT.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block).`when`(HAS_SILK_TOUCH),
                        LootTableReference.lootTableReference(HybridAquaticLootTables.VENT_LOOT_ID)
                    )
                )
            )
        }

        // message in a bottle
        add(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block).`when`(HAS_SILK_TOUCH).apply(
                            CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy(VARIANT_KEY, "$BLOCK_ENTITY_TAG.$VARIANT_KEY")
                                .copy(MESSAGE_KEY, "$BLOCK_ENTITY_TAG.$MESSAGE_KEY")
                        ),
                        LootItem.lootTableItem(HybridAquaticItems.SEA_MESSAGE_BOOK.get()).apply(
                            CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy("$MESSAGE_KEY.tag.$SEA_MESSAGE_KEY", SEA_MESSAGE_KEY)
                        )
                    )
                ).build()
            )
        }

        // crate
        add(HybridAquaticBlocks.CRAB_POT.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.CRAB_POT_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem())
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.HYBRID_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.OAK_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.OAK_CRATE_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem())
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.BIRCH_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.SPRUCE_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.DARK_OAK_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID)
                            .`when`(

                                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                            ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.ACACIA_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.JUNGLE_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.MANGROVE_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID)
                            .`when`(
                                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                            ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.CHERRY_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootTableReference.lootTableReference(HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        // generate remaining drops
        BuiltInRegistries.BLOCK
            .filter(filterHybridAquatic(BuiltInRegistries.BLOCK))
            .filter { block ->
                block !is WallTorchBlock && block !is BaseCoralWallFanBlock
                        && block.lootTable !in map
            }
            .forEach(::dropSelf)
    }
}
