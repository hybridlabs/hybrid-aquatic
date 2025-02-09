package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.gen.GenerationStep

/**
 * Applies biome modifications to features when initialised.
 */
object FeatureBiomeModifications {
    fun registerBiomeModifications() {
        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.ANEMONE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.SARGASSUM
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.SARGASSUM
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.SARGASSUM_FOREST
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.FLOATING_SARGASSUM
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.GLOWING_PLANKTON
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.RED_ALGAE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.RIVERS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.WATER_LETTUCE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SWAMP),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.WATER_LETTUCE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.MARSHES),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.WATER_LETTUCE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TROPICAL_RIVERS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.JUNGLE),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_OCEAN),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES),
            GenerationStep.Feature.TOP_LAYER_MODIFICATION,
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN),
            GenerationStep.Feature.SURFACE_STRUCTURES,
            HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH
        )

//        BiomeModifications.addFeature(
//            BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN),
//            GenerationStep.Feature.VEGETAL_DECORATION,
//            HybridAquaticPlacedFeatures.DEEP_OCEAN_VEGETATION
//        )
    }
}
