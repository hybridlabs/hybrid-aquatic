@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier
import java.util.concurrent.CompletableFuture

class PlacedFeatureProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        // anemone patch
        entries.add(
            HybridAquaticPlacedFeatures.ANEMONE_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.ANEMONE_PATCH), oceanModifiers(1))
        )
    }

    private fun oceanModifiers(count: Int): List<PlacementModifier> {
        return listOf(
            SquarePlacementModifier.of(),
            PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
            CountPlacementModifier.of(count),
            BiomePlacementModifier.of()
        )
    }

    override fun getName(): String {
        return "Placed Features"
    }
}
