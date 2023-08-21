package dev.hybridlabs.aquatic.features

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.features.config.SeaGenerationFeatureConfig
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.Feature

object HybridAquaticFeatures {
    val SEA_GENERATION: Feature<SeaGenerationFeatureConfig> = addSeaFeature("anemone_generation", SeaGenerationFeature(SeaGenerationFeatureConfig.CODEC))

    private fun addSeaFeature(id: String, feature: Feature<SeaGenerationFeatureConfig>): Feature<SeaGenerationFeatureConfig> {
        return Registry.register(Registries.FEATURE, Identifier(HybridAquatic.MOD_ID, id), feature)
    }
}