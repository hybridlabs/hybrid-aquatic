@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.*
import java.util.concurrent.CompletableFuture

class PlacedFeatureProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
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

        // anemone patch
        entries.add(
            HybridAquaticPlacedFeatures.ANEMONE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.ANEMONE_PATCH),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(2),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.GREEN_ANEMONE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.GREEN_ANEMONE_PATCH),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(2),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.BULL_KELP,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.BULL_KELP),
                listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        // sargassum
        entries.add(
            HybridAquaticPlacedFeatures.SARGASSUM,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.SARGASSUM),
                listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.FLOATING_SARGASSUM,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    NoiseBasedCountPlacement.of(30, 80.0, 0.0),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.WATER_LETTUCE,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.WATER_LETTUCE),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(5),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(5),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.GLOWING_PLANKTON,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.GLOWING_PLANKTON),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    NoiseBasedCountPlacement.of(80, 100.0, 0.0),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.RED_ALGAE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH),
                listOf(
                    NoiseBasedCountPlacement.of(10, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH),
                listOf(
                    NoiseBasedCountPlacement.of(400, 100.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.SEA_LETTUCE_PATCH),
                seaweedModifier(80)
            )
        )

        // giant clam patch
        entries.add(
            HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )
        )

        // sponge patch
        entries.add(
            HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(1),
                )
            )
        )

        // message in a bottle
        entries.add(
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(150),
                )
            )
        )

        // thermal vents
        entries.add(
            HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Int.MIN_VALUE, -2),
                    NoiseBasedCountPlacement.of(10, 100.0, 0.0),
                    BiomeFilter.biome()
                )
            )
        )

        // tide pools
        entries.add(
            HybridAquaticPlacedFeatures.TIDE_POOL,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.TIDE_POOL),
                listOf(
                    CountPlacement.of(32),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(63), VerticalAnchor.absolute(68)),
                    BiomeFilter.biome()
                )
            )
        )
    }

    override fun getName(): String {
        return "Placed Features"
    }
}
