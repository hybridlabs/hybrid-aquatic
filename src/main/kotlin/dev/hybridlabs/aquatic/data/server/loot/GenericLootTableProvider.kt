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
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput, lookup: CompletableFuture<RegistryWrapper.WrapperLookup>) : SimpleFabricLootTableProvider(output, lookup, LootContextTypes.GENERIC) {
    override fun accept(exporter: BiConsumer<RegistryKey<LootTable>, LootTable.Builder>) {
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
            HybridAquaticLootTables.POWDER_BLUE_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.POWDER_BLUE_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.YELLOW_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.YELLOW_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SEAHORSE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SEAHORSE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SUNFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SUNFISH))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.GOLDFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.GOLDFISH))
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
            HybridAquaticLootTables.PARROTFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.PARROTFISH))
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
