package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.GENERIC) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        exporter.accept(
            HybridAquaticLootTables.CRAB_POT_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.CRAB_POT_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.COCONUT_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GHOST_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.YETI_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SPIDER_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.VAMPIRE_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DUNGENESS_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FIDDLER_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLOWER_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.EEL_SCARF)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MOON_JELLYFISH_HAT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MANGLERFISH_FIN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MANGLERFISH_LURE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BULL_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DRIFTWOOD_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.DRIFTWOOD_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_PLANKS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_DOOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_TRAPDOOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.OAK_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.OAK_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.OAK_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ROSE_BUSH)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LILAC)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.PEONY)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LILY_OF_THE_VALLEY)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ALLIUM)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.POPPY)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DANDELION)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.AZURE_BLUET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HELMET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_CHESTPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_LEGGINGS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_BOOTS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_SWORD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_AXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_PICKAXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COOKED_BEEF)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.SPRUCE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SPRUCE_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LARGE_FERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.RED_MUSHROOM)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BROWN_MUSHROOM)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SWEET_BERRIES)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HELMET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_CHESTPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_LEGGINGS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_BOOTS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_SWORD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_AXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_PICKAXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COOKED_BEEF)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.BIRCH_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BIRCH_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ROSE_BUSH)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.PEONY)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LILAC)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LILY_OF_THE_VALLEY)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BROWN_MUSHROOM)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HELMET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_CHESTPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_LEGGINGS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_BOOTS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_SWORD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_AXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_PICKAXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COOKED_RABBIT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.ACACIA_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ACACIA_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HELMET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_CHESTPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_LEGGINGS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_BOOTS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_SWORD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_AXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_PICKAXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COOKED_PORKCHOP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.DARK_OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DARK_OAK_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ROSE_BUSH)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.PEONY)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LILAC)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LILY_OF_THE_VALLEY)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BROWN_MUSHROOM)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.RED_MUSHROOM)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HELMET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_CHESTPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_LEGGINGS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_BOOTS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_SWORD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_AXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_PICKAXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COOKED_BEEF)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.MANGROVE_PROPAGULE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MANGROVE_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.LILY_PAD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MOSS_BLOCK)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HELMET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_CHESTPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_LEGGINGS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_BOOTS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_SWORD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_AXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_PICKAXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.TROPICAL_FISH)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.CHERRY_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.CHERRY_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.PINK_PETALS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.JUNGLE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.JUNGLE_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.VINE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COCOA_BEANS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BAMBOO)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MELON_SLICE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HELMET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_CHESTPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_LEGGINGS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_BOOTS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_SWORD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_AXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_PICKAXE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COOKED_CHICKEN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.TUBE_SPONGE_LOOT_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.TUBE_SPONGE_LOOT_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SPONGE_CHUNK)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.THERMAL_VENT_LOOT_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.THERMAL_VENT_LOOT_ID)
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.RAW_GOLD).weight(1))
                        .with(ItemEntry.builder(Items.RAW_IRON).weight(3))
                        .with(ItemEntry.builder(Items.RAW_COPPER).weight(5))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BLUE_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.BLUE_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BLUE_SPOTTED_STINGRAY,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.UNICORNFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.UNICORNFISH))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SOHAL,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SURGEONFISH_SOHAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.ORANGESHOULDER,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SURGEONFISH_ORANGESHOULDER))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CLAWED_LOBSTER,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.LOBSTER_CLAW))
                )
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CLAWLESS_LOBSTER,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CRAB_DIGGING_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.CRAB_DIGGING_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.AIR).weight(10))
                        .with(ItemEntry.builder(Items.NAUTILUS_SHELL).weight(2))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(3))
                        .with(ItemEntry.builder(Items.IRON_NUGGET).weight(5))
                )
        )
    }
}
