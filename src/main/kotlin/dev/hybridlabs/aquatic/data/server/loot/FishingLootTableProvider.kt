package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.loot.entry.MessageInABottleItemEntry
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.LocationCheckLootCondition
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.predicate.entity.LocationPredicate
import net.minecraft.util.Identifier
import net.minecraft.world.biome.BiomeKeys
import java.util.function.BiConsumer

class FishingLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.FISHING) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        val needsJungle = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.JUNGLE)
        )

        val needsSparseJungle = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.SPARSE_JUNGLE)
        )

        val needsBambooJungle = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.BAMBOO_JUNGLE)
        )

        val needsSwamp = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.SWAMP)
        )

        val needsMangroveSwamp = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.MANGROVE_SWAMP)
        )

        val needsBeach = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.BEACH)
        )

        val needsOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.OCEAN)
        )

        val needsDeepOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_OCEAN)
        )

        val needsColdOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.COLD_OCEAN)
        )

        val needsDeepColdOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_COLD_OCEAN)
        )

        val needsFrozenOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.FROZEN_OCEAN)
        )

        val needsDeepFrozenOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_FROZEN_OCEAN)
        )

        val needsLukewarmOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.LUKEWARM_OCEAN)
        )

        val needsDeepLukewarmOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_LUKEWARM_OCEAN)
        )

        val needsWarmOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.WARM_OCEAN)
        )

        val needsRiver = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create().biome(BiomeKeys.RIVER)
        )

        // fishing fish loot table extension
        exporter.accept(
            HybridAquaticLootTables.FISHING_FISH_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_FISH_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LIONFISH)
                                .weight(2)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GOURAMI)
                                .weight(1)
                                .conditionally(needsJungle)
                                .conditionally(needsSparseJungle)
                                .conditionally(needsBambooJungle)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLASHLIGHT_FISH)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsOcean)
                                .conditionally(needsDeepOcean)
                                .conditionally(needsLukewarmOcean)
                                .conditionally(needsDeepLukewarmOcean)
                                .conditionally(needsColdOcean)
                                .conditionally(needsDeepColdOcean)
                                .conditionally(needsFrozenOcean)
                                .conditionally(needsDeepFrozenOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DISCUS)
                                .weight(1)
                                .conditionally(needsJungle)
                                .conditionally(needsSparseJungle)
                                .conditionally(needsBambooJungle)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BETTA)
                                .weight(1)
                                .conditionally(needsSwamp)
                                .conditionally(needsMangroveSwamp)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DANIO)
                                .weight(1)
                                .conditionally(needsJungle)
                                .conditionally(needsSparseJungle)
                                .conditionally(needsBambooJungle)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEON_TETRA)
                                .weight(5)
                                .conditionally(needsJungle)
                                .conditionally(needsSparseJungle)
                                .conditionally(needsBambooJungle)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MAHI)
                                .weight(2)
                                .conditionally(needsLukewarmOcean)
                                .conditionally(needsDeepLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TUNA)
                                .weight(2)
                                .conditionally(needsDeepOcean)
                                .conditionally(needsDeepColdOcean)
                                .conditionally(needsDeepFrozenOcean)
                                .conditionally(needsDeepLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OPAH)
                                .weight(1)
                                .conditionally(needsLukewarmOcean)
                                .conditionally(needsDeepLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ROCKFISH)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsOcean)
                                .conditionally(needsDeepOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
                                .weight(1)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MORAY_EEL)
                                .weight(1)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEEDLEFISH)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                                .conditionally(needsLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.PIRANHA)
                                .weight(2)
                                .conditionally(needsJungle)
                                .conditionally(needsSparseJungle)
                                .conditionally(needsBambooJungle)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ANGLERFISH)
                                .weight(1)
                                .conditionally(needsDeepOcean)
                                .conditionally(needsDeepLukewarmOcean)
                                .conditionally(needsDeepColdOcean)
                                .conditionally(needsDeepFrozenOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BARRELEYE)
                                .weight(1)
                                .conditionally(needsDeepOcean)
                                .conditionally(needsDeepLukewarmOcean)
                                .conditionally(needsDeepColdOcean)
                                .conditionally(needsDeepFrozenOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRAGONFISH)
                                .weight(1)
                                .conditionally(needsDeepOcean)
                                .conditionally(needsDeepLukewarmOcean)
                                .conditionally(needsDeepColdOcean)
                                .conditionally(needsDeepFrozenOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_TANG)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_SOHAL)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                                .conditionally(needsLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_ORANGESHOULDER)
                                .weight(3)
                                .conditionally(needsWarmOcean)
                                .conditionally(needsLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.CLOWNFISH)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.UNICORNFISH)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                                .conditionally(needsLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.COWFISH)
                                .weight(1)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TRIGGERFISH)
                                .weight(3)
                                .conditionally(needsBeach)
                                .conditionally(needsWarmOcean)
                                .conditionally(needsLukewarmOcean)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TIGER_BARB)
                                .weight(2)
                                .conditionally(needsJungle)
                                .conditionally(needsSparseJungle)
                                .conditionally(needsBambooJungle)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OSCAR)
                                .weight(2)
                                .conditionally(needsJungle)
                                .conditionally(needsSparseJungle)
                                .conditionally(needsBambooJungle)
                        )
                )
        )

        // fishing treasure loot table extension
        exporter.accept(
            HybridAquaticLootTables.FISHING_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.CRAB_POT)
                            .weight(3)
                            .conditionally(needsOcean)
                            .conditionally(needsDeepOcean)
                            .conditionally(needsLukewarmOcean)
                            .conditionally(needsDeepLukewarmOcean)
                            .conditionally(needsWarmOcean)
                            .conditionally(needsColdOcean)
                            .conditionally(needsDeepColdOcean)
                            .conditionally(needsFrozenOcean)
                            .conditionally(needsDeepFrozenOcean)
                        )
                        .with(ItemEntry.builder(HybridAquaticItems.HYBRID_CRATE)
                            .weight(3)
                            .conditionally(needsOcean)
                            .conditionally(needsDeepOcean)
                            .conditionally(needsLukewarmOcean)
                            .conditionally(needsDeepLukewarmOcean)
                            .conditionally(needsWarmOcean)
                            .conditionally(needsColdOcean)
                            .conditionally(needsDeepColdOcean)
                            .conditionally(needsFrozenOcean)
                            .conditionally(needsDeepFrozenOcean)
                        )
                        .with(ItemEntry.builder(HybridAquaticItems.OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.SPRUCE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.BIRCH_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.DARK_OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.ACACIA_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.JUNGLE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.MANGROVE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.CHERRY_CRATE))
                        .with(MessageInABottleItemEntry.builder())
                )
        )
    }
}