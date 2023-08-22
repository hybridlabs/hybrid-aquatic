package dev.hybridlabs.aquatic.features

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.features.config.SeaGenerationFeatureConfig
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig

@Suppress("SameParameterValue")
object HybridAquaticFeatures {
    //val SEA_GENERATION = registerFeatureType("sea_feature", SeaGenerationFeature(SeaGenerationFeatureConfig.CODEC))

    private fun <C: FeatureConfig, F: Feature<C>> registerFeatureType(name: String, feature: F): F {
        return Registry.register(Registries.FEATURE, Identifier(HybridAquatic.MOD_ID, name), feature)
    }

    fun addGeneration() {
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.VEGETAL_DECORATION,
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(HybridAquatic.MOD_ID, "anemone_placed_feature"))
        )
    }
}