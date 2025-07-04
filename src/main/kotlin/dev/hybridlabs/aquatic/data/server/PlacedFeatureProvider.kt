@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.placementmodifier.*
import java.util.concurrent.CompletableFuture

class PlacedFeatureProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {

        fun seaweedModifier(count: Int): List<PlacementModifier> {
            return listOf(
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                CountPlacementModifier.of(count),
                BiomePlacementModifier.of()
            )
        }

        // anemone patch
        entries.add(
            HybridAquaticPlacedFeatures.ANEMONE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.ANEMONE_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(2),
                    BiomePlacementModifier.of(),
                )
            )
        )

        // sugar kelp
        entries.add(
            HybridAquaticPlacedFeatures.SUGAR_KELP,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.SUGAR_KELP),
                listOf(
                    NoiseBasedCountPlacementModifier.of(120, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        // bull kelp
        entries.add(
            HybridAquaticPlacedFeatures.BULL_KELP,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.BULL_KELP),
                listOf(
                    NoiseBasedCountPlacementModifier.of(120, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        // sargassum
        entries.add(
            HybridAquaticPlacedFeatures.SARGASSUM,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.SARGASSUM),
                listOf(
                    NoiseBasedCountPlacementModifier.of(10, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.SARGASSUM_FOREST,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.SARGASSUM),
                listOf(
                    NoiseBasedCountPlacementModifier.of(120, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.FLOATING_SARGASSUM,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    NoiseBasedCountPlacementModifier.of(30, 80.0, 0.0),
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.WATER_LETTUCE,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.WATER_LETTUCE),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    CountPlacementModifier.of(5),
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    CountPlacementModifier.of(5),
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.GLOWING_PLANKTON,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.GLOWING_PLANKTON),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    NoiseBasedCountPlacementModifier.of(80, 100.0, 0.0),
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.RED_ALGAE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH),
                listOf(
                    NoiseBasedCountPlacementModifier.of(10, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH),
                listOf(
                    NoiseBasedCountPlacementModifier.of(400, 100.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
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
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                    BiomePlacementModifier.of(),
                )
            )
        )

        // sponge patch
        entries.add(
            HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                )
            )
        )

        // message in a bottle
        entries.add(
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    RarityFilterPlacementModifier.of(150),
                )
            )
        )

        // thermal vents
        entries.add(
            HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.BOTTOM_TO_120_RANGE,
                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -2),
                    NoiseBasedCountPlacementModifier.of(10, 100.0, 0.0),
                    BiomePlacementModifier.of(),
                )
            )
        )
    }

    override fun getName(): String {
        return "Placed Features"
    }
}
