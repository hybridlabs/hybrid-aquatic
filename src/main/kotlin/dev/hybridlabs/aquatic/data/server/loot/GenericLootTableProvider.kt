package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.TagEntry
import net.minecraft.loot.function.EnchantWithLevelsLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.function.SetPotionLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.potion.Potions
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.GENERIC) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        exporter.accept(
            HybridAquaticLootTables.CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.COCOA_BEANS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.NAUTILUS_SHELL)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLDEN_APPLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.GOLDEN_CARROT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SKELETON_SKULL)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.CREEPER_HEAD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ZOMBIE_HEAD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.PIGLIN_HEAD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ACACIA_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BIRCH_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DARK_OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.JUNGLE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SPRUCE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.CHERRY_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BAMBOO)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                        .with(TagEntry.expandBuilder(HybridAquaticItemTags.PLUSHIES))
                        .with(TagEntry.expandBuilder(HybridAquaticItemTags.LURE_ITEMS))
                        .with(
                            ItemEntry.builder(Items.BOOK)
                                .apply(
                                    EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0f))
                                        .allowTreasureEnchantments()
                                )
                        )
                        .with(
                            ItemEntry.builder(Items.CACTUS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 8.0f)))
                        )
                        .with(TagEntry.expandBuilder(HybridAquaticItemTags.IRON_TOOLS))
                        .with(
                            ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.POTION)
                                .apply(SetPotionLootFunction.builder(Potions.WATER_BREATHING))
                        )
                        .with(
                            ItemEntry.builder(Items.POTION)
                                .apply(SetPotionLootFunction.builder(Potions.LUCK))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HYDROTHERMAL_VENT_LOOT_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.HYDROTHERMAL_VENT_LOOT_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.RAW_COPPER)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.RAW_IRON)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                            )
                        .with(
                            ItemEntry.builder(Items.RAW_GOLD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
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
