@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.world.gen.feature.HAConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HAPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.util.valueproviders.ClampedNormalInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.placement.*
import net.minecraft.world.level.material.Fluids
import java.util.concurrent.CompletableFuture

@Suppress("DEPRECATION")
class PlacedFeatureProvider(
    output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>,
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
            HAPlacedFeatures.TIDE_POOLS, PlacedFeature(
                entries.ref(HAConfiguredFeatures.TIDE_POOLS), listOf(
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
            HAPlacedFeatures.BOULDERS, PlacedFeature(
                entries.ref(HAConfiguredFeatures.BOULDER), listOf(
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
            HAPlacedFeatures.ANEMONES, PlacedFeature(
                entries.ref(HAConfiguredFeatures.ANEMONES), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(10),
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.DUNEGRASS_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.DUNEGRASS_PATCH), listOf(
                    NoiseBasedCountPlacement.of(150, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
                )
            )
        )

        //#region Kelp
        entries.add(
            HAPlacedFeatures.BULL_KELP, PlacedFeature(
                entries.ref(HAConfiguredFeatures.BULL_KELP), listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.DELESSERIA, PlacedFeature(
                entries.ref(HAConfiguredFeatures.DELESSERIA), listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.SARGASSUM, PlacedFeature(
                entries.ref(HAConfiguredFeatures.SARGASSUM), listOf(
                    NoiseBasedCountPlacement.of(80, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
        )
        //#endregion

        //#region Floating Plants
        entries.add(
            HAPlacedFeatures.FLOATING_SARGASSUM, PlacedFeature(
                entries.ref(HAConfiguredFeatures.FLOATING_SARGASSUM), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    NoiseBasedCountPlacement.of(30, 80.0, 0.0),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.WATER_LETTUCE, PlacedFeature(
                entries.ref(HAConfiguredFeatures.WATER_LETTUCE), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(3),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.WATER_HYACINTH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.WATER_HYACINTH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(3),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.JUNGLE_LILY_PAD, PlacedFeature(
                entries.ref(HAConfiguredFeatures.JUNGLE_LILY_PAD), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(1),
                    BiomeFilter.biome()
                )
            )
        )
        //#endregion

        entries.add(
            HAPlacedFeatures.SAND_CIRCLE, PlacedFeature(
                entries.ref(HAConfiguredFeatures.SAND_CIRCLE), listOf(
                    RarityFilter.onAverageOnceEvery(2),
                    InSquarePlacement.spread(),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.AERATED_SAND_CIRCLE, PlacedFeature(
                entries.ref(HAConfiguredFeatures.AERATED_SAND_CIRCLE), listOf(
                    RarityFilter.onAverageOnceEvery(5),
                    InSquarePlacement.spread(),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),

                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.matchesFluids(
                            BlockPos(0, 1, 0),
                            Fluids.WATER
                        )
                    ),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.SULFUR_DEPOSIT, PlacedFeature(
                entries.ref(HAConfiguredFeatures.SULFUR_DEPOSIT), listOf(
                    CountPlacement.of(16),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.DISK_SUSPICIOUS_SAND, PlacedFeature(
                entries.ref(HAConfiguredFeatures.SUSPICIOUS_SAND_DISK), listOf(
                    InSquarePlacement.spread(),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.SEA_LETTUCE_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.SEA_LETTUCE_PATCH), seaweedModifier(80)
            )
        )

        //#region Shellfish
        entries.add(
            HAPlacedFeatures.GIANT_CLAM_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.GIANT_CLAM_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(3),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.OYSTER_BED, PlacedFeature(
                entries.ref(HAConfiguredFeatures.OYSTER_BED), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(3),
                    BiomeFilter.biome()
                )
            )
        )
        //#endregion

        //#region Mussels
        entries.add(
            HAPlacedFeatures.TIDE_POOL_MUSSEL_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.TIDE_POOL_MUSSEL_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
                )
            )
        )

        //#region Sponges
        entries.add(
            HAPlacedFeatures.TUBE_SPONGE_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.TUBE_SPONGE_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(3)
                )
            )
        )

         entries.add(
            HAPlacedFeatures.GLASS_SPONGE_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.GLASS_SPONGE_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                     RarityFilter.onAverageOnceEvery(8)
                )
            )
        )

         entries.add(
            HAPlacedFeatures.HARP_SPONGE_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.HARP_SPONGE_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                     RarityFilter.onAverageOnceEvery(5)
                )
            )
        )

         entries.add(
            HAPlacedFeatures.PING_PONG_SPONGE_PATCH, PlacedFeature(
                entries.ref(HAConfiguredFeatures.PING_PONG_SPONGE_PATCH), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                     RarityFilter.onAverageOnceEvery(5)
                )
            )
        )
        //#endregion

        // message in a bottle
        entries.add(
            HAPlacedFeatures.MESSAGE_IN_A_BOTTLE, PlacedFeature(
                entries.ref(HAConfiguredFeatures.MESSAGE_IN_A_BOTTLE), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(150),
                )
            )
        )

        //#region Thermal Vents
        entries.add(
            HAPlacedFeatures.THERMAL_VENT_CAVES, PlacedFeature(
                entries.ref(HAConfiguredFeatures.THERMAL_VENT_PATCH), listOf(
                    InSquarePlacement.spread(),
                    CountOnEveryLayerPlacement.of(3),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, Int.MIN_VALUE, -64),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.THERMAL_VENT_TRENCHES, PlacedFeature(
                entries.ref(HAConfiguredFeatures.THERMAL_VENT_PATCH), listOf(
                    InSquarePlacement.spread(),
                    CountOnEveryLayerPlacement.of(1),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, Int.MIN_VALUE, -64),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.TRENCH_BRINE_POOLS,
            PlacedFeature(
                entries.ref(HAConfiguredFeatures.BRINE_POOL),
                listOf(
                    InSquarePlacement.spread(),
                    CountOnEveryLayerPlacement.of(1),
                    SurfaceRelativeThresholdFilter.of(
                        Heightmap.Types.WORLD_SURFACE_WG,
                        Int.MIN_VALUE,
                        -64
                    ),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.CAVE_BRINE_POOLS,
            PlacedFeature(
                entries.ref(HAConfiguredFeatures.BRINE_POOL),
                listOf(
                    InSquarePlacement.spread(),
                    CountOnEveryLayerPlacement.of(3),
                    SurfaceRelativeThresholdFilter.of(
                        Heightmap.Types.WORLD_SURFACE_WG,
                        Int.MIN_VALUE,
                        -64
                    ),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.RED_BRINE_POOL,
            PlacedFeature(
                entries.ref(HAConfiguredFeatures.RED_BRINE_POOL),
                listOf()
            )
        )

        entries.add(
            HAPlacedFeatures.ORANGE_BRINE_POOL,
            PlacedFeature(
                entries.ref(HAConfiguredFeatures.ORANGE_BRINE_POOL),
                listOf()
            )
        )

        entries.add(
            HAPlacedFeatures.YELLOW_BRINE_POOL,
            PlacedFeature(
                entries.ref(HAConfiguredFeatures.YELLOW_BRINE_POOL),
                listOf()
            )
        )
        //#endregion

        //#region Red Algae
        entries.add(
            HAPlacedFeatures.RED_MEADOW_VEGETATION, PlacedFeature(
                entries.ref(HAConfiguredFeatures.RED_MEADOW_VEGETATION), seaweedModifier(256)
            )
        )
        //#endregion

        //#region Corals
        entries.add(
            HAPlacedFeatures.CORAL_REEF_VEGETATION, PlacedFeature(
                entries.ref(HAConfiguredFeatures.CORAL_REEF_VEGETATION), listOf(
                    CountOnEveryLayerPlacement.of(10),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.DEEP_OCEAN_VEGETATION, PlacedFeature(
                entries.ref(HAConfiguredFeatures.DEEP_OCEAN_VEGETATION), listOf(
                    CountOnEveryLayerPlacement.of(10),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.BLEACHED_REEF_VEGETATION, PlacedFeature(
                entries.ref(HAConfiguredFeatures.BLEACHED_REEF_VEGETATION), listOf(
                    NoiseBasedCountPlacement.of(20, 400.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    BiomeFilter.biome()
                )
            )
        )
        //#endregion

        //#region Mounds
        entries.add(
            HAPlacedFeatures.CORAL_MOUND, PlacedFeature(
                entries.ref(HAConfiguredFeatures.CORAL_MOUND), listOf(
                    RarityFilter.onAverageOnceEvery(3),
                    NoiseBasedCountPlacement.of(10, 90.0, 0.0),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    RandomOffsetPlacement.of(ConstantInt.of(7), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-5, 5), ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.CORAL_LAYER, PlacedFeature(
                entries.ref(HAConfiguredFeatures.CORAL_LAYER), listOf(
                    CountPlacement.of(16),
                    InSquarePlacement(),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    BiomeFilter.biome()

                )

            )
        )

        entries.add(
            HAPlacedFeatures.MOUND, PlacedFeature(
                entries.ref(HAConfiguredFeatures.MOUND), listOf(
                    RarityFilter.onAverageOnceEvery(3),
                    NoiseBasedCountPlacement.of(10, 90.0, 0.0),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    RandomOffsetPlacement.of(ConstantInt.of(7), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-5, 5), ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )
        )

        entries.add(
            HAPlacedFeatures.WHITE_MOUND, PlacedFeature(
                entries.ref(HAConfiguredFeatures.WHITE_MOUND), listOf(
                    RarityFilter.onAverageOnceEvery(3),
                    NoiseBasedCountPlacement.of(10, 90.0, 0.0),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    RandomOffsetPlacement.of(ConstantInt.of(7), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-5, 5), ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )
        )
        //#endregion
    }

    override fun getName(): String {
        return "Placed Features"
    }
}
