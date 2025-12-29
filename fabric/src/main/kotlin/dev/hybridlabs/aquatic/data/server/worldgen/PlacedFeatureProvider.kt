@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.util.valueproviders.ClampedNormalInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement
import net.minecraft.world.level.levelgen.placement.RarityFilter
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter
import java.util.concurrent.CompletableFuture

class PlacedFeatureProvider(
    output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: HolderLookup.Provider, entries: Entries) {

        fun seaweedModifier(count: Int): List<PlacementModifier> {
            return listOf(
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                CountPlacement.of(count),
                BiomeFilter.biome()
            )
        }

        // tide pools
        entries.add(
            HybridAquaticPlacedFeatures.TIDE_POOLS, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.TIDE_POOLS), listOf(
                    CountPlacement.of(24),
                    RandomOffsetPlacement.of(ConstantInt.of(8), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(ClampedNormalInt.of(0.0f,3.0f,-10,10),ConstantInt.ZERO),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(63), VerticalAnchor.absolute(68)),
                    CountPlacement.of(3),
                    RandomOffsetPlacement.of(UniformInt.of(-12,12),ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )
        )

        // tide pool boulder
        entries.add(
            HybridAquaticPlacedFeatures.BOULDERS, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.BOULDER), listOf(
                    CountPlacement.of(UniformInt.of(4,6)),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(53), VerticalAnchor.absolute(53)),
                    RandomOffsetPlacement.of(ConstantInt.of(8),ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-3,3),UniformInt.of(-3,1)),
                    SurfaceWaterDepthFilter.forMaxDepth(6),
                    BiomeFilter.biome()
                )

            )
        )

        // anemone patch
        entries.add(
            HybridAquaticPlacedFeatures.ANEMONES, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.ANEMONES), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(10),
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DUNEGRASS_PATCH, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.DUNEGRASS_PATCH), listOf(
                    NoiseBasedCountPlacement.of(150, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.BULL_KELP, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.BULL_KELP), listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        // sargassum
        entries.add(
            HybridAquaticPlacedFeatures.SARGASSUM, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.SARGASSUM), listOf(
                    NoiseBasedCountPlacement.of(80, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.FLOATING_SARGASSUM, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    NoiseBasedCountPlacement.of(30, 80.0, 0.0),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.WATER_LETTUCE, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.WATER_LETTUCE), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(5),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(5),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.RED_ALGAE_PATCH, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH), listOf(
                    NoiseBasedCountPlacement.of(10, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH), listOf(
                    NoiseBasedCountPlacement.of(120, 100.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.SEA_LETTUCE_PATCH), seaweedModifier(80)
            )
        )

        // giant clam patch
        entries.add(
            HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )
        )

        // sponge patch
        entries.add(
            HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(1),
                )
            )
        )

        // message in a bottle
        entries.add(
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(150),
                )
            )
        )

        // thermal vents
        entries.add(
            HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH, PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH), listOf(
                    CountOnEveryLayerPlacement.of(5),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, Int.MIN_VALUE, -64),
                    BiomeFilter.biome()
                )
            )
        )
    }

    override fun getName(): String {
        return "Placed Features"
    }
}
