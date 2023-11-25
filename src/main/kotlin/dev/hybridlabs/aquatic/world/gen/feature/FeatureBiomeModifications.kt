package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.gen.GenerationStep

/**
 * Applies biome modifications to features when initialised.
 */
object FeatureBiomeModifications {
    init {
        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.ANEMONE_SPAWN_BIOMES),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.ANEMONE_PATCH
        )
        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.HYDROTHERMAL_VENT_SPAWN_BIOMES),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.HYDROTHERMAL_VENTS
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.MESSAGE_IN_A_BOTTLE_SPAWN_BIOMES),
            GenerationStep.Feature.TOP_LAYER_MODIFICATION,
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE
        )
    }
}
