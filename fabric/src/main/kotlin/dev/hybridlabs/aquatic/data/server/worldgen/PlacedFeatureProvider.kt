@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.placement.*
import java.util.concurrent.CompletableFuture

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
            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.ANEMONES,
                reg.get(HybridAquaticConfiguredFeatures.ANEMONES).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    RarityFilter.onAverageOnceEvery(10),
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.DUNEGRASS_PATCH,
                reg.get(HybridAquaticConfiguredFeatures.DUNEGRASS_PATCH).get(), listOf(
                    NoiseBasedCountPlacement.of(150, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.BULL_KELP,
                reg.get(HybridAquaticConfiguredFeatures.BULL_KELP).get(), listOf(
                    NoiseBasedCountPlacement.of(120, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.SARGASSUM,
                reg.get(HybridAquaticConfiguredFeatures.SARGASSUM).get(), listOf(
                    NoiseBasedCountPlacement.of(80, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )


            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.FLOATING_SARGASSUM,
                reg.get(HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    NoiseBasedCountPlacement.of(30, 80.0, 0.0),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.WATER_LETTUCE,
                reg.get(HybridAquaticConfiguredFeatures.WATER_LETTUCE).get(), listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(5),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD,
                reg.get(HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD).get(),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    CountPlacement.of(5),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.RED_ALGAE_PATCH,
                reg.get(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH).get(),
                listOf(
                    NoiseBasedCountPlacement.of(10, 80.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW,
                reg.get(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH).get(),
                listOf(
                    NoiseBasedCountPlacement.of(120, 100.0, 0.0),
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH,
                reg.get(HybridAquaticConfiguredFeatures.SEA_LETTUCE_PATCH).get(),
                seaweedModifier(80)
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH,
                reg.get(HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH).get(),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(1),
                    BiomeFilter.biome()
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH,
                reg.get(HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH).get(),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                    CountPlacement.of(1),
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE,
                reg.get(HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE).get(),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    RarityFilter.onAverageOnceEvery(150),
                )
            )

            PlacementUtils.register(
                bootstrap,
                HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH,
                reg.get(HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH).get(),
                listOf(
                    CountOnEveryLayerPlacement.of(5),
                    SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, Int.MIN_VALUE, -64),
                    BiomeFilter.biome()
                )
            )
        }
    }


    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        val reg = registries.lookup(Registries.PLACED_FEATURE).get()


        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.ANEMONES))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.BULL_KELP))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.DUNEGRASS_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.FLOATING_SARGASSUM))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.RED_ALGAE_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.SARGASSUM))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticPlacedFeatures.WATER_LETTUCE))
    }

    override fun getName(): String {
        return "Placed Features"
    }
}