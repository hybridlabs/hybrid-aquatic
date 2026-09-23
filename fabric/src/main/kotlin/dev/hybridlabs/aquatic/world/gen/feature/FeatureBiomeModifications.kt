package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.CommonClass
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature

/**
 * Applies biome modifications to features when initialised.
 */
object FeatureBiomeModifications {
    // Vanilla vegetation that Hybrid Aquatic's own ocean features replace.
    // NeoForge removes the same features with the biome modifiers in data/hybrid_aquatic/neoforge/biome_modifier.
    private val DEEP_OCEAN_REMOVALS = placedFeatures(
        "seagrass_deep_cold", "seagrass_deep_warm", "seagrass_deep", "seagrass_simple", "kelp_cold", "kelp_warm"
    )
    private val WARM_OCEAN_REMOVALS = placedFeatures("warm_ocean_vegetation")

    fun registerBiomeModifications() {
        for (addition in BiomeFeatureAddition.builtIn) {

            BiomeModifications.addFeature(
                BiomeSelectors.tag(addition.biomeTag),
                addition.step,
                addition.placedFeature
            )
        }

        BiomeModifications.create(CommonClass.locate("remove_ocean_vegetation"))
            .add(ModificationPhase.REMOVALS, BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN)) { context ->
                DEEP_OCEAN_REMOVALS.forEach { context.generationSettings.removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, it) }
            }
            .add(ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(Biomes.WARM_OCEAN)) { context ->
                WARM_OCEAN_REMOVALS.forEach { context.generationSettings.removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, it) }
            }
    }

    private fun placedFeatures(vararg paths: String): List<ResourceKey<PlacedFeature>> =
        paths.map { ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.withDefaultNamespace(it)) }
}
