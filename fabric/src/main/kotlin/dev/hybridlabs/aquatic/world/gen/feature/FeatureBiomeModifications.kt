package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.levelgen.GenerationStep

/**
 * Applies biome modifications to features when initialised.
 */
object FeatureBiomeModifications {
    fun registerBiomeModifications() {
        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.ANEMONE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TEMPERATE_OCEANS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.GREEN_ANEMONE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.SARGASSUM
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.FLOATING_SARGASSUM
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.BULL_KELP
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.GLOWING_PLANKTON
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TROPICAL_OCEANS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.RED_ALGAE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.RIVERS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.WATER_LETTUCE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SWAMP),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.WATER_LETTUCE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.MARSHES),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.WATER_LETTUCE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TROPICAL_RIVERS),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.JUNGLE),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_OCEAN),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES),
            GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN),
            GenerationStep.Decoration.SURFACE_STRUCTURES,
            HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH
        )
    }
}
