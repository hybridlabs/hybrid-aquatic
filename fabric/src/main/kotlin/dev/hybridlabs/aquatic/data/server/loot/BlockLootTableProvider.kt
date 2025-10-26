package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.MESSAGE_KEY
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.VARIANT_KEY
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.SeaMessageBookItem.Companion.SEA_MESSAGE_KEY
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.ItemTags
import net.minecraft.world.level.block.BaseCoralWallFanBlock
import net.minecraft.world.level.block.WallTorchBlock
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.CopyCustomDataFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.MatchTool
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(output: FabricDataOutput, registryLookup: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(output, registryLookup) {

    override fun generate() {
        // anemone
        add(HybridAquaticBlocks.ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block)).conditionally(hasShearsOrSilkTouch().build()).build()
            )
        }

        add(HybridAquaticBlocks.STRAWBERRY_ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build()).build()
            )
        }

        add(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build()).build()
            )
        }

        add(HybridAquaticPlatformBlocks.DUNEGRASS.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build()).build()
            )
        }

        add(HybridAquaticPlatformBlocks.CATTAIL.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build()).build()
            )
        }

        add(HybridAquaticBlocks.RED_ALGAE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build()).build()
            )
        }

        add(HybridAquaticBlocks.SEA_LETTUCE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build()).build()
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
                    .conditionally(hasShearsOrSilkTouch().build()).build()
            )
        }

        add(HybridAquaticBlocks.GLOWSLIME_BLOCK.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .build()
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
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_STAIRS.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get())
        createSingleItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())
        add(
            HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get(),
            createSlabItemTable(HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get())
        )
        add(
            HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get(),
            createDoorTable(HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get())
        )

        createSingleItemTable(HybridAquaticBlocks.GLOWSTICK.get())

        //endregion

        //region corals

        for (block in listOf(
            HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN.get(),

            HybridAquaticBlocks.THORN_CORAL_BLOCK.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get(),
            HybridAquaticBlocks.THORN_CORAL.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL.get(),
            HybridAquaticBlocks.THORN_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN.get(),

            HybridAquaticBlocks.SUN_CORAL_BLOCK.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get(),
            HybridAquaticBlocks.SUN_CORAL.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL.get(),
            HybridAquaticBlocks.SUN_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL_FAN.get(),

            HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get(),
            HybridAquaticBlocks.BUTTON_CORAL.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL.get(),
            HybridAquaticBlocks.BUTTON_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN.get(),

            HybridAquaticBlocks.ROSE_CORAL_BLOCK.get(),
            HybridAquaticBlocks.DEAD_ROSE_CORAL_BLOCK.get(),
            HybridAquaticBlocks.ROSE_CORAL.get(),
            HybridAquaticBlocks.DEAD_ROSE_CORAL.get(),
            HybridAquaticBlocks.ROSE_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_ROSE_CORAL_FAN.get(),

            HybridAquaticBlocks.LEAF_CORAL_BLOCK.get(),
            HybridAquaticBlocks.DEAD_LEAF_CORAL_BLOCK.get(),
            HybridAquaticBlocks.LEAF_CORAL.get(),
            HybridAquaticBlocks.DEAD_LEAF_CORAL.get(),
            HybridAquaticBlocks.LEAF_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_LEAF_CORAL_FAN.get(),
        )) {
            add(block, createSilkTouchOnlyTable(block))
        }

        //endregion

        // thermal vents
        add(HybridAquaticBlocks.THERMAL_VENT.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block).`when`(hasSilkTouch()),
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.VENT_LOOT_ID
                            )
                        )
                    )
                )
            )
        }

        // message in a bottle
        add(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block).`when`(hasSilkTouch()).apply(
                            CopyCustomDataFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy(VARIANT_KEY, VARIANT_KEY)
                                .copy(MESSAGE_KEY, MESSAGE_KEY)
                        ),
                        LootItem.lootTableItem(HybridAquaticItems.SEA_MESSAGE_BOOK.get()).apply(
                            CopyCustomDataFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy(MESSAGE_KEY, SEA_MESSAGE_KEY)
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.CRAB_POT_TREASURE_ID
                            )
                        ).`when`(
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(Registries.LOOT_TABLE, HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID)
                        ).`when`(
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.OAK_CRATE_TREASURE_ID
                            )
                        ).`when`(
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID
                            )
                        ).`when`(
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID
                            )
                        ).`when`(
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID
                            )
                        )
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID
                            )
                        ).`when`(
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HybridAquaticBlocks.BAMBOO_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.BAMBOO_CRATE_TREASURE_ID
                            )
                        ).`when`(
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID
                            )
                        )
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
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID
                            )
                        ).`when`(
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
