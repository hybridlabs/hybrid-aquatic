package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput, lookup: CompletableFuture<RegistryWrapper.WrapperLookup>) : SimpleFabricLootTableProvider(output, lookup, LootContextTypes.GENERIC) {
    override fun accept(lookup: RegistryWrapper.WrapperLookup, exporter: BiConsumer<RegistryKey<LootTable>, LootTable.Builder>) {
        exporter.accept(
            HybridAquaticLootTables.CRAB_POT_TREASURE_ID,
            LootTable.builder()
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
                        .with(
                            ItemEntry.builder(HybridAquaticItems.RAW_SHRIMP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LOBSTER_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .bonusRolls(UniformLootNumberProvider.create(0.0f, 1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.MANGLERFISH_FIN))
                        .with(ItemEntry.builder(HybridAquaticItems.MANGLERFISH_LURE))
                        .with(ItemEntry.builder(HybridAquaticItems.EEL_SCARF))
                        .with(ItemEntry.builder(HybridAquaticItems.MOON_JELLYFISH_HAT))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.OAK_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.OAK_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.APPLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.SPRUCE_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SPRUCE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SWEET_BERRIES)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.BIRCH_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BIRCH_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.PUMPKIN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.ACACIA_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ACACIA_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BEETROOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.DARK_OAK_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DARK_OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.APPLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 12.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.MANGROVE_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MANGROVE_PROPAGULE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FROGSPAWN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.CHERRY_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.CHERRY_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.PINK_PETALS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 12.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID,
            LootTable.builder()
                // the universal crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(HybridAquaticItems.BARBED_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.GLOWING_HOOK))
                        .with(ItemEntry.builder(HybridAquaticItems.MAGNETIC_HOOK))
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKULL_BANNER_PATTERN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                )
                // the type-specific crate loot pool
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                            ItemEntry.builder(Items.JUNGLE_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.JUNGLE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MELON)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.COCOA_BEANS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                )
                // the chance to get driftwood out of a crate
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                )
                // the rare chance to get a special item
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE))
                        .with(ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.VENT_LOOT_ID,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1F))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SULFUR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                        )
                )
                .pool(
                    LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(Items.RAW_GOLD).weight(1))
                        .with(ItemEntry.builder(Items.RAW_IRON).weight(3))
                        .with(ItemEntry.builder(Items.RAW_COPPER).weight(5))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SURGEONFISH_BLUE_TANG,
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
            HybridAquaticLootTables.SURGEONFISH_UNICORNFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.UNICORN_FISH))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SURGEONFISH_SOHAL,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SURGEONFISH_SOHAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SURGEONFISH_LINED,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SURGEONFISH_LINED))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SURGEONFISH_ORANGESHOULDER,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SURGEONFISH_ORANGESHOULDER))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SURGEONFISH_POWDER_BLUE_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.POWDER_BLUE_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SURGEONFISH_YELLOW_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.YELLOW_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.KOI,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.KOI))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CARP,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.CARP))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.YELLOWFIN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.YELLOWFIN_TUNA))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BLUEFIN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.BLUEFIN_TUNA))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SPOTTED_EAGLE_RAY,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SPOTTED_EAGLE_RAY))
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
            HybridAquaticLootTables.HERMIT_CRAB_SKULL,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.SKELETON_SKULL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HERMIT_CRAB_SHELL,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.NAUTILUS_SHELL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_FIRE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.FIRE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_BRAIN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.BRAIN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_TUBE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.TUBE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_BUBBLE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.BUBBLE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_HORN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.HORN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_THORN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.THORN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_LOPHELIA,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.LOPHELIA_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CRAB_DIGGING_TREASURE_ID,
            LootTable.builder()
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
