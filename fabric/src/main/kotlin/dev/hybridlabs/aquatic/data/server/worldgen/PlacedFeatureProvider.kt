@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.world.gen.feature.HAConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HAPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
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

    companion object {

        fun seaweedModifier(count: Int): List<PlacementModifier> {
            return listOf(
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                CountPlacement.of(count),
                BiomeFilter.biome()
            )
        }

        fun bootstrapPlacedFeatures(bootstrap: BootstrapContext<PlacedFeature>) {
            val reg = bootstrap.lookup(Registries.CONFIGURED_FEATURE)

            // tide pools
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.TIDE_POOLS,
                reg.get(HAConfiguredFeatures.TIDE_POOLS).get(), listOf(
                    CountPlacement.of(24),
                    RandomOffsetPlacement.of(ConstantInt.of(8), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(ClampedNormalInt.of(0.0f, 3.0f, -10, 10), ConstantInt.ZERO),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(63), VerticalAnchor.absolute(68)),
                    CountPlacement.of(3),
                    RandomOffsetPlacement.of(UniformInt.of(-12, 12), ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )

            // tide pool boulder
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.BOULDERS,
                reg.get(HAConfiguredFeatures.BOULDER).get(), listOf(
                    CountPlacement.of(UniformInt.of(4, 6)),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(53), VerticalAnchor.absolute(53)),
                    RandomOffsetPlacement.of(ConstantInt.of(8), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-3, 3), UniformInt.of(-3, 1)),
                    SurfaceWaterDepthFilter.forMaxDepth(6),
                    BiomeFilter.biome()
                )
            )

            // anemone patch
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.ANEMONES,
                reg.get(HAConfiguredFeatures.ANEMONES).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(10),
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.DUNEGRASS_PATCH,
                reg.get(HAConfiguredFeatures.DUNEGRASS_PATCH).get(), listOf(
                    NoiseBasedCountPlacement.of(150, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
                )
            )

            //#region Kelp
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.BULL_KELP,
                reg.get(HAConfiguredFeatures.BULL_KELP).get(), listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.DELESSERIA,
                reg.get(HAConfiguredFeatures.DELESSERIA).get(), listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.SARGASSUM,
                reg.get(HAConfiguredFeatures.SARGASSUM).get(), listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )
            //#endregion

            //#region Floating Plants
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.FLOATING_SARGASSUM,
                reg.get(HAConfiguredFeatures.FLOATING_SARGASSUM).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    NoiseBasedCountPlacement.of(30, 80.0, 0.0),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.WATER_LETTUCE,
                reg.get(HAConfiguredFeatures.WATER_LETTUCE).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(3),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.JUNGLE_LILY_PAD,
                reg.get(HAConfiguredFeatures.JUNGLE_LILY_PAD).get(),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.WATER_HYACINTH,
                reg.get(HAConfiguredFeatures.WATER_LETTUCE).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(3),
                    BiomeFilter.biome()
                )
            )
            //#endregion

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.SAND_CIRCLE,
                reg.get(HAConfiguredFeatures.SAND_CIRCLE).get(), listOf(
                    RarityFilter.onAverageOnceEvery(2),
                    InSquarePlacement.spread(),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.AERATED_SAND_CIRCLE,
                reg.get(HAConfiguredFeatures.AERATED_SAND_CIRCLE).get(), listOf(
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

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.SULFUR_DEPOSIT,
                reg.get(HAConfiguredFeatures.SULFUR_DEPOSIT).get(), listOf(
                    CountPlacement.of(16),
                    InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.DISK_SUSPICIOUS_SAND,
                reg.get(HAConfiguredFeatures.SUSPICIOUS_SAND_DISK).get(), listOf(
                    InSquarePlacement.spread(),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.SEA_LETTUCE_PATCH,
                reg.get(HAConfiguredFeatures.SEA_LETTUCE_PATCH).get(), seaweedModifier(80)
            )

            //#region Shellfish
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.GIANT_CLAM_PATCH,
                reg.get(HAConfiguredFeatures.GIANT_CLAM_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(3),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.OYSTER_BED,
                reg.get(HAConfiguredFeatures.OYSTER_BED).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(3),
                    BiomeFilter.biome()
                )
            )
            //#endregion

            //#region Mussels
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.TIDE_POOL_MUSSEL_PATCH,
                reg.get(HAConfiguredFeatures.TIDE_POOL_MUSSEL_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
                )
            )

            //#region Sponges
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.TUBE_SPONGE_PATCH,
                reg.get(HAConfiguredFeatures.TUBE_SPONGE_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(3)
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.GLASS_SPONGE_PATCH,
                reg.get(HAConfiguredFeatures.GLASS_SPONGE_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(8)
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.HARP_SPONGE_PATCH,
                reg.get(HAConfiguredFeatures.HARP_SPONGE_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(5)
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.PING_PONG_SPONGE_PATCH,
                reg.get(HAConfiguredFeatures.PING_PONG_SPONGE_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(5)
                )
            )
            //#endregion

            // message in a bottle
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.MESSAGE_IN_A_BOTTLE,
                reg.get(HAConfiguredFeatures.MESSAGE_IN_A_BOTTLE).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(150),
                )
            )

            //#region Thermal Vents
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.THERMAL_VENT_CAVES,
                reg.get(HAConfiguredFeatures.THERMAL_VENT_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    CountOnEveryLayerPlacement.of(3),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, Int.MIN_VALUE, -64),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.THERMAL_VENT_TRENCHES,
                reg.get(HAConfiguredFeatures.THERMAL_VENT_PATCH).get(), listOf(
                    InSquarePlacement.spread(),
                    CountOnEveryLayerPlacement.of(1),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, Int.MIN_VALUE, -64),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.TRENCH_BRINE_POOLS,

                reg.get(HAConfiguredFeatures.BRINE_POOL).get(),
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

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.CAVE_BRINE_POOLS,

                reg.get(HAConfiguredFeatures.BRINE_POOL).get(),
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

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.RED_BRINE_POOL,
                reg.get(HAConfiguredFeatures.RED_BRINE_POOL).get(),
                listOf()
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.ORANGE_BRINE_POOL,
                reg.get(HAConfiguredFeatures.ORANGE_BRINE_POOL).get(),
                listOf()
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.YELLOW_BRINE_POOL,
                reg.get(HAConfiguredFeatures.YELLOW_BRINE_POOL).get(),
                listOf()
            )
            //#endregion

            //#region Red Algae
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.RED_ALGAE_PATCH,
                reg.get(HAConfiguredFeatures.RED_ALGAE_PATCH).get(), seaweedModifier(256)
            )
            //#endregion

            //#region Corals
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.CORAL_REEF_VEGETATION,
                reg.get(HAConfiguredFeatures.CORAL_REEF_VEGETATION).get(), listOf(
                    CountOnEveryLayerPlacement.of(10),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.DEEP_OCEAN_VEGETATION,
                reg.get(HAConfiguredFeatures.DEEP_OCEAN_VEGETATION).get(), listOf(
                    CountOnEveryLayerPlacement.of(10),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.BLEACHED_REEF_VEGETATION,
                reg.get(HAConfiguredFeatures.BLEACHED_REEF_VEGETATION).get(), listOf(
                    NoiseBasedCountPlacement.of(20, 400.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    BiomeFilter.biome()
                )
            )
            //#endregion

            //#region Mounds
            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.CORAL_MOUND,
                reg.get(HAConfiguredFeatures.CORAL_MOUND).get(), listOf(
                    RarityFilter.onAverageOnceEvery(3),
                    NoiseBasedCountPlacement.of(10, 90.0, 0.0),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    RandomOffsetPlacement.of(ConstantInt.of(7), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-5, 5), ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.CORAL_LAYER,
                reg.get(HAConfiguredFeatures.CORAL_LAYER).get(), listOf(
                    CountPlacement.of(16),
                    InSquarePlacement(),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    BiomeFilter.biome()

                )

            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.MOUND,
                reg.get(HAConfiguredFeatures.MOUND).get(), listOf(
                    RarityFilter.onAverageOnceEvery(3),
                    NoiseBasedCountPlacement.of(10, 90.0, 0.0),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    RandomOffsetPlacement.of(ConstantInt.of(7), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-5, 5), ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HAPlacedFeatures.WHITE_MOUND,
                reg.get(HAConfiguredFeatures.WHITE_MOUND).get(), listOf(
                    RarityFilter.onAverageOnceEvery(3),
                    NoiseBasedCountPlacement.of(10, 90.0, 0.0),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                    RandomOffsetPlacement.of(ConstantInt.of(7), ConstantInt.ZERO),
                    RandomOffsetPlacement.of(UniformInt.of(-5, 5), ConstantInt.ZERO),
                    BiomeFilter.biome()
                )
            )
            //#endregion
        }
    }


    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        val reg = registries.lookup(Registries.PLACED_FEATURE).get()

        entries.add(reg.getOrThrow(HAPlacedFeatures.ANEMONES))
        entries.add(reg.getOrThrow(HAPlacedFeatures.BULL_KELP))
        entries.add(reg.getOrThrow(HAPlacedFeatures.DELESSERIA))
        entries.add(reg.getOrThrow(HAPlacedFeatures.DUNEGRASS_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.FLOATING_SARGASSUM))
        entries.add(reg.getOrThrow(HAPlacedFeatures.SARGASSUM))
        entries.add(reg.getOrThrow(HAPlacedFeatures.GIANT_CLAM_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.JUNGLE_LILY_PAD))
        entries.add(reg.getOrThrow(HAPlacedFeatures.MESSAGE_IN_A_BOTTLE))
        entries.add(reg.getOrThrow(HAPlacedFeatures.RED_ALGAE_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.SEA_LETTUCE_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.THERMAL_VENT_CAVES))
        entries.add(reg.getOrThrow(HAPlacedFeatures.THERMAL_VENT_TRENCHES))
        entries.add(reg.getOrThrow(HAPlacedFeatures.RED_BRINE_POOL))
        entries.add(reg.getOrThrow(HAPlacedFeatures.ORANGE_BRINE_POOL))
        entries.add(reg.getOrThrow(HAPlacedFeatures.YELLOW_BRINE_POOL))
        entries.add(reg.getOrThrow(HAPlacedFeatures.CAVE_BRINE_POOLS))
        entries.add(reg.getOrThrow(HAPlacedFeatures.TRENCH_BRINE_POOLS))
        entries.add(reg.getOrThrow(HAPlacedFeatures.GLASS_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.HARP_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.PING_PONG_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.TUBE_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HAPlacedFeatures.WATER_LETTUCE))
        entries.add(reg.getOrThrow(HAPlacedFeatures.DISK_SUSPICIOUS_SAND))
        entries.add(reg.getOrThrow(HAPlacedFeatures.OYSTER_BED))
    }

    override fun getName(): String {
        return "Placed Features"
    }
}