package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature

data class BiomeFeatureAddition(
    val biomeTag: TagKey<Biome>, val step: GenerationStep.Decoration, val placedFeature: ResourceKey<PlacedFeature>,
) {
    companion object {
        val builtIn = buildList {
            //#region Anemones
            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.CORAL_REEF,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.ANEMONES
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.ANEMONES
                ),
            )
            //#endregion

            //#region Sponges
            add(
                BiomeFeatureAddition(
                    BiomeTags.IS_OCEAN,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    BiomeTags.IS_DEEP_OCEAN,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.GLASS_SPONGE_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    BiomeTags.IS_DEEP_OCEAN,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.HARP_SPONGE_PATCH
                ),
            )
            //#endregion

            //#region Giant Clams
            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.CORAL_REEF,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SEAGRASS_BED,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.RED_MEADOW,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
                ),
            )
            //#endregion

            //#region Oyster Beds
            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.WARM_OCEAN,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.CORAL_REEF,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SEAGRASS_BED,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.RED_MEADOW,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.OYSTER_BED
                ),
            )
            //#endregion

            //#region River Plants
            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SEAGRASS_BED,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.SEA_LETTUCE_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.RIVERS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.WATER_LETTUCE
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SWAMP,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.WATER_LETTUCE
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.MARSHES,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.WATER_LETTUCE
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.TROPICAL_RIVERS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.JUNGLE_LILY_PAD
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.TROPICAL_RIVERS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.WATER_HYACINTH
                ),
            )
            //#endregion

            //#region Kelp Plants
            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.SARGASSUM
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.FLOATING_SARGASSUM
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.BULL_KELP
                ),
            )
            //#endregion

            //#region Vents & Sulfur
            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SULFURIC_CAVE,
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    HybridAquaticPlacedFeatures.SULFUR_DEPOSIT
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SULFURIC_CAVE,
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    HybridAquaticPlacedFeatures.THERMAL_VENT_CAVES
                )
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.ALL_TRENCHES,
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    HybridAquaticPlacedFeatures.THERMAL_VENT_TRENCHES
                )
            )
            //#endregion

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SANDY_BEACHES,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HybridAquaticPlacedFeatures.DUNEGRASS_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.SANDY_BEACHES,
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    HybridAquaticPlacedFeatures.DISK_SUSPICIOUS_SAND
                ),
            )

            add(
                BiomeFeatureAddition(
                    BiomeTags.IS_DEEP_OCEAN,
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    HybridAquaticPlacedFeatures.MOUND
                ),
            )

            add(
                BiomeFeatureAddition(
                    HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES,
                    GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                    HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE
                )
            )
        }
    }
}