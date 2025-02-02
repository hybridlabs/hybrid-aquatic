package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.NoiseBasedCountPlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier
import net.minecraft.world.gen.placementmodifier.SurfaceThresholdFilterPlacementModifier

/**
 * A registry of placed features for Hybrid Aquatic.
 */
object HybridAquaticPlacedFeatures {
    val ANEMONE_PATCH = register("anemone_patch")

    val SARGASSUM = register("sargassum")
    val SARGASSUM_FOREST = register("sargassum_forest")
    val FLOATING_SARGASSUM = register("floating_sargassum")

    val WATER_LETTUCE = register("water_lettuce")

    val JUNGLE_LILY_PAD = register("jungle_lily_pad")

    val GLOWING_PLANKTON = register("glowing_plankton")

    val RED_ALGAE_PATCH = register("red_algae_patch")
    val RED_ALGAE_MEADOW = register("red_algae_meadow")

    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")

    val THERMAL_VENT_PATCH = register("thermal_vent_patch")

    val TUBE_SPONGE_PATCH = register("sponge_patch")
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")
//    val BRINE_LAKE = register("brine_lake")
//    val DEEP_CORAL_TREE = register("deep_coral_tree")
//    val DEEP_CORAL_CLAW = register("deep_coral_claw")
//    val DEEP_CORAL_MUSHROOM = register("deep_coral_mushroom")
//    val DEEP_OCEAN_VEGETATION = register("deep_ocean_vegetation")

    private fun register(id: String): RegistryKey<PlacedFeature> {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }

    fun bootstrap(registry: Registerable<PlacedFeature>) {
        fun seaweedModifier(count: Int): List<PlacementModifier> {
            return listOf(
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                CountPlacementModifier.of(count),
                BiomePlacementModifier.of()
            )
        }

        val placedFeatureLookup = registry.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)

        // anemone patch
        registry.register(
            ANEMONE_PATCH,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.ANEMONE_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(2),
                    BiomePlacementModifier.of(),
                )
            )
        )

        // sargassum
        registry.register(
            SARGASSUM,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.SARGASSUM),
                listOf(
                    NoiseBasedCountPlacementModifier.of(10, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            SARGASSUM_FOREST,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.SARGASSUM),
                listOf(
                    NoiseBasedCountPlacementModifier.of(120, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            FLOATING_SARGASSUM,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    NoiseBasedCountPlacementModifier.of(30, 80.0, 0.0),
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            WATER_LETTUCE,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.WATER_LETTUCE),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    CountPlacementModifier.of(5),
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            JUNGLE_LILY_PAD,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    CountPlacementModifier.of(5),
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            GLOWING_PLANKTON,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.GLOWING_PLANKTON),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    NoiseBasedCountPlacementModifier.of(80, 100.0, 0.0),
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            RED_ALGAE_PATCH,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH),
                listOf(
                    NoiseBasedCountPlacementModifier.of(10, 80.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            RED_ALGAE_MEADOW,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH),
                listOf(
                    NoiseBasedCountPlacementModifier.of(400, 100.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of(),
                )
            )
        )

        registry.register(
            SEA_LETTUCE_PATCH,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.SEA_LETTUCE_PATCH),
                seaweedModifier(80)
            )
        )

        // giant clam patch
        registry.register(
            GIANT_CLAM_PATCH,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                    BiomePlacementModifier.of(),
                )
            )
        )

        // sponge patch
        registry.register(
            TUBE_SPONGE_PATCH,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                )
            )
        )

        // message in a bottle
        registry.register(
            MESSAGE_IN_A_BOTTLE,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    RarityFilterPlacementModifier.of(150),
                )
            )
        )

        // thermal vents
        registry.register(
            THERMAL_VENT_PATCH,
            PlacedFeature(
                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.BOTTOM_TO_120_RANGE,
                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -2),
                    NoiseBasedCountPlacementModifier.of(10, 100.0, 0.0),
                    BiomePlacementModifier.of(),
                )
            )
        )

//        // brine lake
//        registry.register(
//            HybridAquaticPlacedFeatures.BRINE_LAKE,
//            PlacedFeature(placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.BRINE_LAKE),
//                listOf(
//                    SquarePlacementModifier.of(),
//                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -32),
//                    NoiseBasedCountPlacementModifier.of(10, 100.0, 0.0),
//                )
//            )
//        )

//        registry.register(
//            HybridAquaticPlacedFeatures.DEEP_CORAL_MUSHROOM,
//            PlacedFeature(placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.DEEP_CORAL_MUSHROOM),
//                listOf(
//                    SquarePlacementModifier.of(),
//                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
//                )
//            )
//        )
//
//        registry.register(
//            HybridAquaticPlacedFeatures.DEEP_CORAL_TREE,
//            PlacedFeature(placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.DEEP_CORAL_TREE),
//                listOf(
//                    SquarePlacementModifier.of(),
//                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
//                )
//            )
//        )
//
//        registry.register(
//            HybridAquaticPlacedFeatures.DEEP_CORAL_CLAW,
//            PlacedFeature(placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.DEEP_CORAL_CLAW),
//                listOf(
//                    SquarePlacementModifier.of(),
//                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
//                )
//            )
//        )
//
//        registry.register(
//            HybridAquaticPlacedFeatures.DEEP_OCEAN_VEGETATION,
//            PlacedFeature(
//                placedFeatureLookup.getOrThrow(HybridAquaticConfiguredFeatures.DEEP_OCEAN_VEGETATION),
//                listOf(
//                    SquarePlacementModifier.of(),
//                    NoiseBasedCountPlacementModifier.of(20, 200.0, 0.0),
//                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -32),
//                )
//            )
//        )
    }
}
