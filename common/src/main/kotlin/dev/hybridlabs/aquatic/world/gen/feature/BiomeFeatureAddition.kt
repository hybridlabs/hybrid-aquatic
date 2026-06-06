package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HABiomeTags
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes.config
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
                    HABiomeTags.CORAL_REEF,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.ANEMONES
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.ANEMONES
                ),
            )
            //#endregion

            //#region Sponges
            add(
                BiomeFeatureAddition(
                    BiomeTags.IS_OCEAN,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.TUBE_SPONGE_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.ALL_TRENCHES,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.GLASS_SPONGE_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.ALL_TRENCHES,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.HARP_SPONGE_PATCH
                ),
            )

                add(
                    BiomeFeatureAddition(
                        HABiomeTags.ALL_TRENCHES,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.PING_PONG_SPONGE_PATCH
                    ),
                )

            if (config.config.featureConfig.generateBrinePools) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.ALL_TRENCHES,
                        GenerationStep.Decoration.LAKES,
                        HAPlacedFeatures.TRENCH_BRINE_POOLS
                    ),
                )

                add(
                    BiomeFeatureAddition(
                        HABiomeTags.SULFURIC_CAVE,
                        GenerationStep.Decoration.LAKES,
                        HAPlacedFeatures.CAVE_BRINE_POOLS
                    ),
                )
            }
            //#endregion

            //#region Shellfish
            add(
                BiomeFeatureAddition(
                    HABiomeTags.TIDE_POOLS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.TIDE_POOL_MUSSEL_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.CORAL_REEF,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.GIANT_CLAM_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.SEAGRASS_BED,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.GIANT_CLAM_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.RED_MEADOW,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.GIANT_CLAM_PATCH
                ),
            )
            //#endregion

            //#region Oyster Beds
            add(
                BiomeFeatureAddition(
                    HABiomeTags.SHALLOW_COLD_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.SHALLOW_WARM_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.CORAL_REEF,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.SEAGRASS_BED,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.RED_MEADOW,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.OYSTER_BED
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.OYSTER_BED
                ),
            )
            //#endregion

            //#region River Plants
            add(
                BiomeFeatureAddition(
                    HABiomeTags.SEAGRASS_BED,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.SEA_LETTUCE_PATCH
                ),
            )

            if (config.config.featureConfig.generateRiverPlants) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.RIVERS,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.WATER_LETTUCE
                    ),
                )

                add(
                    BiomeFeatureAddition(
                        HABiomeTags.SWAMP,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.WATER_LETTUCE
                    ),
                )

                add(
                    BiomeFeatureAddition(
                        HABiomeTags.MARSHES,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.WATER_LETTUCE
                    ),
                )

                add(
                    BiomeFeatureAddition(
                        HABiomeTags.TROPICAL_RIVERS,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.JUNGLE_LILY_PAD
                    ),
                )

                add(
                    BiomeFeatureAddition(
                        HABiomeTags.TROPICAL_RIVERS,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.WATER_HYACINTH
                    ),
                )
            }
            //#endregion

            //#region Kelp Plants
            if (config.config.featureConfig.generateSargassum) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.SHALLOW_LUKEWARM_OCEANS,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.SARGASSUM
                    ),
                )
            }

            if (config.config.featureConfig.generateFloatingSargassum) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.DEEP_LUKEWARM_OCEANS,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.FLOATING_SARGASSUM
                    ),
                )
            }

            if (config.config.featureConfig.generateBullKelp) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.BULL_KELP
                    ),
                )
            }

            if (config.config.featureConfig.generateDelesseria) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.SHALLOW_COLD_OCEANS,
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        HAPlacedFeatures.DELESSERIA
                    ),
                )
            }
            //#endregion

            //#region Vents & Sulfur
            add(
                BiomeFeatureAddition(
                    HABiomeTags.SULFURIC_CAVE,
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    HAPlacedFeatures.SULFUR_DEPOSIT
                ),
            )

            if (config.config.featureConfig.generateThermalVents) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.SULFURIC_CAVE,
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        HAPlacedFeatures.THERMAL_VENT_CAVES
                    )
                )

                add(
                    BiomeFeatureAddition(
                        HABiomeTags.ALL_TRENCHES,
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        HAPlacedFeatures.THERMAL_VENT_TRENCHES
                    )
                )
            }
            //#endregion

            add(
                BiomeFeatureAddition(
                    HABiomeTags.SANDY_BEACHES,
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    HAPlacedFeatures.DUNEGRASS_PATCH
                ),
            )

            add(
                BiomeFeatureAddition(
                    HABiomeTags.SANDY_BEACHES,
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    HAPlacedFeatures.DISK_SUSPICIOUS_SAND
                ),
            )

            if (config.config.featureConfig.generateMounds) {
                add(
                    BiomeFeatureAddition(
                        BiomeTags.IS_DEEP_OCEAN,
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        HAPlacedFeatures.MOUND
                    ),
                )
            }

            if (config.config.featureConfig.generateBottles) {
                add(
                    BiomeFeatureAddition(
                        HABiomeTags.BOTTLE_SPAWN_BIOMES,
                        GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                        HAPlacedFeatures.MESSAGE_IN_A_BOTTLE
                    )
                )
            }
        }
    }
}