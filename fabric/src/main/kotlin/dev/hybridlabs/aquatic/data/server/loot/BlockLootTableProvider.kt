package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.MESSAGE_KEY
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.VARIANT_KEY
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.SeaMessageBookItem.Companion.SEA_MESSAGE_KEY
import dev.hybridlabs.aquatic.loot.HALootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.advancements.critereon.EnchantmentPredicate
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.advancements.critereon.MinMaxBounds
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.BaseCoralWallFanBlock
import net.minecraft.world.level.block.PotatoBlock
import net.minecraft.world.level.block.WallTorchBlock
import net.minecraft.world.level.storage.loot.IntRange
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.CopyCustomDataFunction
import net.minecraft.world.level.storage.loot.functions.LimitCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.MatchTool
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(output: FabricDataOutput, registryLookup: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(output, registryLookup) {

    override fun generate() {

        //#region Anemones
        add(HABlocks.ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HABlocks.STRAWBERRY_ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HABlocks.GIANT_GREEN_ANEMONE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }
        //#endregion

        //#region Plants
        add(HAPlatformBlocks.DUNEGRASS.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HAPlatformBlocks.TALL_DUNEGRASS.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HAPlatformBlocks.CATTAIL.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HABlocks.SHORT_RED_ALGAE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HABlocks.RED_ALGAE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HABlocks.SEA_LETTUCE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HABlocks.BULL_KELP.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .build()
            )
        }

        add(HABlocks.SARGASSUM.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .build()
            )
        }

        add(HABlocks.DELESSERIA.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .build()
            )
        }

        add(HABlocks.FLOATING_SARGASSUM.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .conditionally(hasShearsOrSilkTouch().build())
                    .build()
            )
        }

        add(HABlocks.BONE_WORMS.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(block))
                    .build()
            )
        }

        add(HABlocks.CLAMS.get()) { block ->
            val ageCondition: LootItemCondition.Builder =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, 7))

            applyExplosionDecay(
                block,
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block))).withPool(
                    LootPool.lootPool().`when`(ageCondition).add(
                        LootItem.lootTableItem(HAItems.CLAM.get()).apply(
                            ApplyBonusCount.addBonusBinomialDistributionCount(
                                Enchantments.BLOCK_FORTUNE,
                                0.5714286f,
                                3
                            )
                        )
                    )
                )
            )
        }

        add(HABlocks.MUSSELS.get()) { block ->
            val ageCondition: LootItemCondition.Builder =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PotatoBlock.AGE, 7))

            applyExplosionDecay(
                block,
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block))).withPool(
                    LootPool.lootPool().`when`(ageCondition).add(
                        LootItem.lootTableItem(HAItems.MUSSEL.get()).apply(
                            ApplyBonusCount.addBonusBinomialDistributionCount(
                                Enchantments.BLOCK_FORTUNE,
                                0.5714286f,
                                3
                            )
                        )
                    )
                )
            )
        }

        add(HABlocks.WILD_MUSSELS.get()) { _ ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(HAItems.MUSSEL.get()))
            )
        }
        //#endregion

        //#region Wood
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_LOG.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_WOOD.get())
        createSingleItemTable(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
        createSingleItemTable(HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_STAIRS.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_BUTTON.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_FENCE.get())
        createSingleItemTable(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())
        add(
            HAPlatformBlocks.DRIFTWOOD_SLAB.get(),
            createSlabItemTable(HAPlatformBlocks.DRIFTWOOD_SLAB.get())
        )
        add(
            HAPlatformBlocks.DRIFTWOOD_DOOR.get(),
            createDoorTable(HAPlatformBlocks.DRIFTWOOD_DOOR.get())
        )
        //#endregion

        //#region Corals
        add(HABlocks.LOPHELIA_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_LOPHELIA_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        add(HABlocks.BAMBOO_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_BAMBOO_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        add(HABlocks.ZIGZAG_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_ZIGZAG_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        add(HABlocks.THORN_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_THORN_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        add(HABlocks.SUN_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_SUN_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        add(HABlocks.BUTTON_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_BUTTON_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        add(HABlocks.ROSE_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_ROSE_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        add(HABlocks.LEAF_CORAL_BLOCK.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(block)
                            .`when`(hasSilkTouch())
                    )
                    .add(
                        LootItem.lootTableItem(HABlocks.DEAD_LEAF_CORAL_BLOCK.get())
                            .`when`(hasSilkTouch().invert())
                    )
            )
        }

        for (block in listOf(
            HABlocks.LOPHELIA_CORAL.get(),
            HABlocks.DEAD_LOPHELIA_CORAL.get(),
            HABlocks.LOPHELIA_CORAL_FAN.get(),
            HABlocks.DEAD_LOPHELIA_CORAL_FAN.get(),

            HABlocks.BAMBOO_CORAL.get(),
            HABlocks.DEAD_BAMBOO_CORAL.get(),
            HABlocks.BAMBOO_CORAL_FAN.get(),
            HABlocks.DEAD_BAMBOO_CORAL_FAN.get(),

            HABlocks.ZIGZAG_CORAL.get(),
            HABlocks.DEAD_ZIGZAG_CORAL.get(),
            HABlocks.ZIGZAG_CORAL_FAN.get(),
            HABlocks.DEAD_ZIGZAG_CORAL_FAN.get(),

            HABlocks.THORN_CORAL.get(),
            HABlocks.DEAD_THORN_CORAL.get(),
            HABlocks.THORN_CORAL_FAN.get(),
            HABlocks.DEAD_THORN_CORAL_FAN.get(),

            HABlocks.SUN_CORAL.get(),
            HABlocks.DEAD_SUN_CORAL.get(),
            HABlocks.SUN_CORAL_FAN.get(),
            HABlocks.DEAD_SUN_CORAL_FAN.get(),

            HABlocks.BUTTON_CORAL.get(),
            HABlocks.DEAD_BUTTON_CORAL.get(),
            HABlocks.BUTTON_CORAL_FAN.get(),
            HABlocks.DEAD_BUTTON_CORAL_FAN.get(),

            HABlocks.ROSE_CORAL.get(),
            HABlocks.DEAD_ROSE_CORAL.get(),
            HABlocks.ROSE_CORAL_FAN.get(),
            HABlocks.DEAD_ROSE_CORAL_FAN.get(),

            HABlocks.LEAF_CORAL.get(),
            HABlocks.DEAD_LEAF_CORAL.get(),
            HABlocks.LEAF_CORAL_FAN.get(),
            HABlocks.DEAD_LEAF_CORAL_FAN.get(),
        )) {
            add(block, createSilkTouchOnlyTable(block))
        }
        //#endregion

        //#region Thermal Vent
        add(HABlocks.THERMAL_VENT.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block).`when`(hasSilkTouch()),
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HALootTables.VENT_LOOT_ID
                            )
                        )
                    )
                )
            )
        }

        add(HABlocks.GIANT_THERMAL_VENT.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block).`when`(hasSilkTouch()),
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HALootTables.VENT_LOOT_ID
                            )
                        )
                    )
                )
            )
        }

        add(HABlocks.CRYSTALLINE_SULFUR.get()) { block ->
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .add(
                        AlternativesEntry.alternatives(
                            LootItem.lootTableItem(block)
                                .`when`(
                                    MatchTool.toolMatches(
                                        ItemPredicate.Builder.item()
                                            .hasEnchantment(
                                                EnchantmentPredicate(
                                                    Enchantments.SILK_TOUCH,
                                                    MinMaxBounds.Ints.atLeast(1)
                                                )
                                            )
                                    )
                                ),

                            LootItem.lootTableItem(HAItems.SULFUR.get())
                                .apply(
                                    SetItemCountFunction.setCount(
                                        UniformGenerator.between(2f, 5f)
                                    )
                                )
                                .apply(
                                    ApplyBonusCount.addUniformBonusCount(
                                        Enchantments.FORTUNE,
                                        1
                                    )
                                )
                                .apply(
                                    LimitCount.limitCount(
                                        IntRange.range(1, 9)
                                    )
                                )
                        )
                    )
            )
        }

        add(HABlocks.TUBE_WORM.get()) { block ->
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
        //#endregion

        //#region Miscellaneous
        add(HABlocks.MESSAGE_IN_A_BOTTLE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        LootItem.lootTableItem(block).`when`(hasSilkTouch()).apply(
                            CopyCustomDataFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy(VARIANT_KEY, VARIANT_KEY)
                                .copy(MESSAGE_KEY, MESSAGE_KEY)
                        ),
                        LootItem.lootTableItem(HAItems.SEA_MESSAGE_BOOK.get()).apply(
                            CopyCustomDataFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy(MESSAGE_KEY, SEA_MESSAGE_KEY)
                        )
                    )
                ).build()
            )
        }

        createSingleItemTable(HABlocks.GLOWSTICK.get())
        //#endregion

        //#region Crates
        add(HABlocks.CRAB_POT.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                                HALootTables.CRAB_POT_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem())
                    )
                ).build()
            )
        }

        add(HABlocks.HYBRID_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.HYBRID_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.OAK_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.OAK_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem())
                    )
                ).build()
            )
        }

        add(HABlocks.BIRCH_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.BIRCH_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.SPRUCE_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.SPRUCE_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.DARK_OAK_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.DARK_OAK_CRATE_TREASURE_ID
                            )
                        ).`when`(
                                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                            ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.ACACIA_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.ACACIA_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.JUNGLE_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.JUNGLE_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.BAMBOO_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.BAMBOO_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.MANGROVE_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.MANGROVE_CRATE_TREASURE_ID
                            )
                        ).`when`(
                                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                            ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }

        add(HABlocks.CHERRY_CRATE.get()) { block ->
            LootTable.lootTable().pool(
                LootPool.lootPool().add(
                    AlternativesEntry.alternatives(
                        NestedLootTable.lootTableReference(
                            ResourceKey.create(
                                Registries.LOOT_TABLE,
                            HALootTables.CHERRY_CRATE_TREASURE_ID
                            )
                        ).`when`(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.AXES))
                        ),
                        LootItem.lootTableItem(block.asItem()),
                    )
                ).build()
            )
        }
        //#endregion

        add(HABlocks.DECORATIVE_BUBBLE_COLUMN.get()) { block ->
            LootTable.lootTable()
        }

        add(HABlocks.BUBBLE_NET.get()) { block ->
            LootTable.lootTable()
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
