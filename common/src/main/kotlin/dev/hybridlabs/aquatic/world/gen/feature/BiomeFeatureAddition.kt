package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature

data class BiomeFeatureAddition(
    val biomeTag: TagKey<Biome>, val step: GenerationStep.Decoration, val placedFeature: ResourceKey<PlacedFeature>
) {
    companion object {
        val builtIn = listOf(
            BiomeFeatureAddition(
                HybridAquaticBiomeTags.REEF,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.ANEMONES
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.ANEMONES
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.SARGASSUM
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.FLOATING_SARGASSUM
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.BULL_KELP
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.RED_MEADOW,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.RED_ALGAE_MEADOW
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SANDY_BEACHES,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.DUNEGRASS_PATCH
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SEAGRASS_MEADOW,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.RIVERS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.WATER_LETTUCE
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SWAMP,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.WATER_LETTUCE
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.MARSHES,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.WATER_LETTUCE
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.TROPICAL_RIVERS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD
            ),

            //#region Giant Clams
            BiomeFeatureAddition(
                HybridAquaticBiomeTags.REEF,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SEAGRASS_MEADOW,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.RED_MEADOW,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
            ),
            //#endregion

            //#region Oyster Beds
            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.OYSTER_BED
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.WARM_OCEAN,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.OYSTER_BED
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.REEF,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.OYSTER_BED
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SEAGRASS_MEADOW,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.OYSTER_BED
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.RED_MEADOW,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.OYSTER_BED
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.OYSTER_BED
            ),
            //#endregion

            BiomeFeatureAddition(
                BiomeTags.IS_OCEAN,
                GenerationStep.Decoration.VEGETAL_DECORATION,
                HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES,
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE
            ),

            BiomeFeatureAddition(
                HybridAquaticBiomeTags.HAS_THERMAL_VENTS,
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH
            )
        )
    }
}