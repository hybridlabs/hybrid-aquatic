package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.MESSAGE_KEY
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.SeaMessageBookItem.Companion.SEA_MESSAGE_KEY
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.DeadCoralWallFanBlock
import net.minecraft.block.WallTorchBlock
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.MatchToolLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.GroupEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.CopyNbtLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(output: FabricDataOutput, lookup: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricBlockLootTableProvider(output, lookup) {
    override fun generate() {
        val itemLookup = registries.getOrThrow(RegistryKeys.ITEM)

        // anemone
        dropsWithSilkTouchOrShears(HybridAquaticBlocks.ANEMONE)

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

        addDrop(HybridAquaticBlocks.GLOWSTICK)

        //endregion

        //region corals
        addDropWithSilkTouch(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK)
        addDropWithSilkTouch(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK)
        addDropWithSilkTouch(HybridAquaticBlocks.LOPHELIA_CORAL)
        addDropWithSilkTouch(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL)
        addDropWithSilkTouch(HybridAquaticBlocks.LOPHELIA_CORAL_FAN)
        addDropWithSilkTouch(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN)

        addDropWithSilkTouch(HybridAquaticBlocks.THORN_CORAL_BLOCK)
        addDropWithSilkTouch(HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK)
        addDropWithSilkTouch(HybridAquaticBlocks.THORN_CORAL)
        addDropWithSilkTouch(HybridAquaticBlocks.DEAD_THORN_CORAL)
        addDropWithSilkTouch(HybridAquaticBlocks.THORN_CORAL_FAN)
        addDropWithSilkTouch(HybridAquaticBlocks.DEAD_THORN_CORAL_FAN)

        //endregion


        // living sponge
        dropsWithSilkTouch(HybridAquaticBlocks.TUBE_SPONGE,
            ItemEntry.builder(HybridAquaticItems.SPONGE_CHUNK)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
        )


        // thermal vents
        dropsWithSilkTouch(HybridAquaticBlocks.HYDROTHERMAL_VENT,
            GroupEntry.create(
                ItemEntry.builder(Items.RAW_GOLD).weight(1),
                ItemEntry.builder(Items.RAW_IRON).weight(3),
                ItemEntry.builder(Items.RAW_COPPER).weight(5)
            )
        )

        // message in a bottle
        addDrop(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        /*ItemEntry.builder(block).conditionally(createSilkTouchCondition()).apply(
                            CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                .withOperation(VARIANT_KEY, "$BLOCK_ENTITY_TAG_KEY.$VARIANT_KEY")
                                .withOperation(MESSAGE_KEY, "$BLOCK_ENTITY_TAG_KEY.$MESSAGE_KEY")
                        ), TODO */
                        ItemEntry.builder(HybridAquaticItems.SEA_MESSAGE_BOOK).apply(
                            CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                .withOperation("$MESSAGE_KEY.tag.$SEA_MESSAGE_KEY", SEA_MESSAGE_KEY)
                        )
                    )
                )
            )
        }

        // crate
        addDrop(HybridAquaticBlocks.CRAB_POT) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(HybridAquaticItems.COCONUT_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.GHOST_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.YETI_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.SPIDER_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.VAMPIRE_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.DUNGENESS_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.FIDDLER_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.FLOWER_CRAB_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.RAW_SHRIMP)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.LOBSTER_CLAW)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.HYBRID_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(HybridAquaticItems.EEL_SCARF)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.MOON_JELLYFISH_HAT)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.MANGLERFISH_FIN)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.MANGLERFISH_LURE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.BULL_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))),
                                    ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block)
                            ),
                        )
                )
        }

        addDrop(HybridAquaticBlocks.DRIFTWOOD_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_PLANKS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f))),
                                    ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_DOOR)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))),
                                    ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_TRAPDOOR)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.OAK_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(Items.OAK_SAPLING)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.OAK_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.ROSE_BUSH)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.LILAC)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.PEONY)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.LILY_OF_THE_VALLEY)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.ALLIUM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.POPPY)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.DANDELION)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.AZURE_BLUET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.IRON_HELMET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_BOOTS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_SWORD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_AXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_PICKAXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.COOKED_BEEF)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.BIRCH_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(Items.BIRCH_SAPLING)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.BIRCH_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.ROSE_BUSH)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.PEONY)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.LILAC)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.LILY_OF_THE_VALLEY)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.BROWN_MUSHROOM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.IRON_HELMET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_BOOTS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_SWORD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_AXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_PICKAXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.COOKED_RABBIT)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.SPRUCE_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(Items.SPRUCE_SAPLING)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.SPRUCE_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.LARGE_FERN)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.FERN)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.RED_MUSHROOM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.BROWN_MUSHROOM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.SWEET_BERRIES)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.IRON_HELMET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_BOOTS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_SWORD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_AXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_PICKAXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.COOKED_BEEF)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.DARK_OAK_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(Items.DARK_OAK_SAPLING)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.DARK_OAK_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.ROSE_BUSH)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.PEONY)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.LILAC)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.LILY_OF_THE_VALLEY)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.BROWN_MUSHROOM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.RED_MUSHROOM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.IRON_HELMET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_BOOTS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_SWORD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_AXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_PICKAXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.COOKED_BEEF)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.ACACIA_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(ItemEntry.builder(Items.ACACIA_SAPLING)
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.ACACIA_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_HELMET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_BOOTS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_SWORD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_AXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_PICKAXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.COOKED_PORKCHOP)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.JUNGLE_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(Items.JUNGLE_SAPLING)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.JUNGLE_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.VINE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.FERN)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.COCOA_BEANS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.BAMBOO)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.MELON_SLICE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.IRON_HELMET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_BOOTS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_SWORD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_AXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_PICKAXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.COOKED_CHICKEN)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.MANGROVE_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(Items.MANGROVE_PROPAGULE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.MANGROVE_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.LILY_PAD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.FERN)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.MOSS_BLOCK)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f))),
                                    ItemEntry.builder(Items.IRON_HELMET)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_BOOTS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_SWORD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_AXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.IRON_PICKAXE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.TROPICAL_FISH)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        addDrop(HybridAquaticBlocks.CHERRY_CRATE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            AlternativeEntry.builder(
                                GroupEntry.create(
                                    ItemEntry.builder(Items.CHERRY_SAPLING)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))),
                                    ItemEntry.builder(Items.CHERRY_LOG)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f))),
                                    ItemEntry.builder(Items.PINK_PETALS)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                                ).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemLookup, ItemTags.AXES))),
                                ItemEntry.builder(block),
                            )
                        )
                )
        }

        // generate remaining drops
        Registries.BLOCK
            .filter(filterHybridAquatic(Registries.BLOCK))
            .filter { block ->
                block !is WallTorchBlock && block !is DeadCoralWallFanBlock
                        && block.lootTableKey.orElseThrow() !in lootTables.keys
            }
            .forEach(::addDrop)
    }
}
