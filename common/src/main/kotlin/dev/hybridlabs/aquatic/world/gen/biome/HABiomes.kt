package dev.hybridlabs.aquatic.world.gen.biome

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder
import com.terraformersmc.biolith.api.biome.sub.RatioTargets
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.config.ConfigHelper
import dev.hybridlabs.aquatic.tag.HABiomeTags
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.SurfaceRules.*

object HABiomes {
    val config = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)

    //#region Reworked Vanilla Surface Rules
    val WARM_OCEAN_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(Biomes.WARM_OCEAN),
        sequence(
            ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
        )
    )

    val LUKEWARM_OCEAN_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(Biomes.LUKEWARM_OCEAN),
        sequence(
            ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SMOOTH_SANDSTONE.defaultBlockState()))
        )
    )
    //#endregion

    //#region Beach Biome Surface Rules
    val TIDE_POOLS: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tide_pools"))
    val TIDE_POOL_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(TIDE_POOLS),
        sequence(
            ifTrue(ON_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState())),
        )
    )
    //#endregion

    //#region Warm Ocean Biome Surface Rules
    val DEEP_WARM_OCEAN: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("deep_warm_ocean"))
    val DEEP_WARM_OCEAN_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(DEEP_WARM_OCEAN),
        sequence(
            ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
        )
    )

    val SEAGRASS_BED: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("seagrass_bed"))
    val SEAGRASS_BED_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(SEAGRASS_BED),
        sequence(
            ifTrue(ON_FLOOR, state(HABlocks.GRASSY_SAND.get().defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.SAND.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
        )
    )

    val RED_MEADOW: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("red_meadow"))
    val RED_MEADOW_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(RED_MEADOW),
        sequence(
            ifTrue(
                ON_FLOOR,
                state(HABlocks.WHITE_SAND.get().defaultBlockState())
            ),
            ifTrue(UNDER_FLOOR, state(HABlocks.WHITE_SAND.get().defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(HABlocks.WHITE_SANDSTONE.get().defaultBlockState()))
        )
    )

    val CORAL_REEF: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("coral_reef"))
    val CORAL_REEF_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(CORAL_REEF),
        sequence(
            ifTrue(
                ON_FLOOR,
                state(HABlocks.SHORESTONE.get().defaultBlockState())
            ),
            ifTrue(UNDER_FLOOR, state(Blocks.SMOOTH_SANDSTONE.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
        )
    )
    //#endregion

    //#region River Biome Surface Rules
    val TROPICAL_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_river"))
    val TROPICAL_RIVER_SURFACE_RULE: RuleSource =
        ifTrue(isBiome(TROPICAL_RIVER), ifTrue(ON_FLOOR, state(Blocks.MUD.defaultBlockState())))
    //#endregion

    //#region Trench Rules
    val TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("trench"))
    val TRENCH_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(TRENCH),
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.MARINE_SNOW.get().defaultBlockState())),
                state(HABlocks.SCHIST.get().defaultBlockState())
            )
        )

    val WARM_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("warm_trench"))
    val WARM_TRENCH_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(WARM_TRENCH),
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.MARINE_SNOW.get().defaultBlockState())),
                state(HABlocks.SCHIST.get().defaultBlockState())
            )
        )

    val LUKEWARM_TRENCH: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("lukewarm_trench"))
    val LUKEWARM_TRENCH_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(LUKEWARM_TRENCH),
        sequence(
            ifTrue(ON_FLOOR, state(HABlocks.MARINE_SNOW.get().defaultBlockState())),
            state(HABlocks.SCHIST.get().defaultBlockState())
        )
    )

    val COLD_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_trench"))
    val COLD_TRENCH_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(COLD_TRENCH),
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.MARINE_SNOW.get().defaultBlockState())),
                state(HABlocks.SCHIST.get().defaultBlockState())
            )
        )

    val FROZEN_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("frozen_trench"))
    val FROZEN_TRENCH_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(FROZEN_TRENCH),
        sequence(
            ifTrue(ON_FLOOR, state(HABlocks.MARINE_SNOW.get().defaultBlockState())),
            state(HABlocks.SCHIST.get().defaultBlockState())
        )
    )

    val SULFURIC_CAVES: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("sulfuric_caves"))
    //#endregion

    //#region Deep Reefs
    val DEEP_CORAL_REEF: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("deep_coral_reef"))
    val DEEP_CORAL_REEF_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(
                DEEP_CORAL_REEF
            ),
            sequence(
                ifTrue(ON_FLOOR, state(Blocks.TUFF.defaultBlockState()))
            )
        )

    val TROPICAL_DEEP_CORAL_REEF: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_deep_coral_reef"))
    val TROPICAL_DEEP_CORAL_REEF_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(
                TROPICAL_DEEP_CORAL_REEF
            ),
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.CORALSTONE.get().defaultBlockState()))
            )
        )
    //#endregion

    fun addBiomes() {
        //#region River Generation Fixes
        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder.OCEANSIDE
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.DEEP_OCEAN)
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.COLD_OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.COLD_OCEAN)
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.COLD_OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.DEEP_COLD_OCEAN)
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.FROZEN_OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.FROZEN_OCEAN)
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.FROZEN_OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.DEEP_FROZEN_OCEAN)
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.LUKEWARM_OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.LUKEWARM_OCEAN)
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.LUKEWARM_OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.DEEP_LUKEWARM_OCEAN)
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.WARM_OCEAN,
            CriterionBuilder.allOf(
                CriterionBuilder
                    .neighbor(Biomes.WARM_OCEAN)
            )
        )
        //#endregion

        //#region New Rivers
        if (config.config.biomeConfig.generateTropicalRiver) {
            BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                TROPICAL_RIVER,
                CriterionBuilder.allOf(
                    CriterionBuilder
                        .neighbor(BiomeTags.IS_JUNGLE)
                )
            )
        }
        //#endregion

        //#region Beach Biomes
        if (config.config.biomeConfig.generateTidePools) {
            BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                TIDE_POOLS,
                CriterionBuilder.allOf(
                    CriterionBuilder.ratio(
                        RatioTargets.EDGE,
                        0.0f,
                        0.5f
                    ),
                    CriterionBuilder
                        .neighbor(HABiomeTags.LUKEWARM_OCEANS)
                )
            )
        }
        //#endregion

        //#region Warm Ocean Biomes
        if (config.config.biomeConfig.generateSeagrassBed) {
            BiomePlacement.replaceOverworld(
                Biomes.WARM_OCEAN,
                SEAGRASS_BED,
                0.25
            )
        }

        BiomePlacement.replaceOverworld(
            Biomes.WARM_OCEAN,
            CORAL_REEF,
            0.25
        )


        if (config.config.biomeConfig.generateDeepCoralReef) {
            BiomePlacement.replaceOverworld(
                Biomes.WARM_OCEAN,
                RED_MEADOW,
                0.25
            )
        }

        if (config.config.biomeConfig.generateDeepCoralReef) {
            BiomePlacement.replaceOverworld(
                Biomes.DEEP_OCEAN,
                DEEP_CORAL_REEF,
                0.1
            )

            BiomePlacement.replaceOverworld(
                Biomes.DEEP_LUKEWARM_OCEAN,
                TROPICAL_DEEP_CORAL_REEF,
                0.1
            )

            BiomePlacement.replaceOverworld(
                Biomes.DEEP_COLD_OCEAN,
                DEEP_CORAL_REEF,
                0.1
            )

            BiomePlacement.replaceOverworld(
                Biomes.DEEP_FROZEN_OCEAN,
                DEEP_CORAL_REEF,
                0.1
            )
        }
        //#endregion

        //#region Deep Warm Ocean
        if (config.config.biomeConfig.generateDeepWarmOcean) {
            BiomePlacement.addSubOverworld(
                CORAL_REEF,
                DEEP_WARM_OCEAN,
                CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -0.64f,
                    -0.455f
                )
            )

            BiomePlacement.addSubOverworld(
                CORAL_REEF,
                DEEP_WARM_OCEAN,
                CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -1.05f,
                    -0.7f,
                )
            )

            BiomePlacement.addSubOverworld(
                SEAGRASS_BED,
                DEEP_WARM_OCEAN,
                CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -0.64f,
                    -0.455f,
                )
            )

            BiomePlacement.addSubOverworld(
                SEAGRASS_BED,
                DEEP_WARM_OCEAN,
                CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -1.05f,
                    -0.7f,
                )
            )

            BiomePlacement.addSubOverworld(
                RED_MEADOW,
                DEEP_WARM_OCEAN,
                CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -0.64f,
                    -0.455f,
                )
            )

            BiomePlacement.addSubOverworld(
                RED_MEADOW,
                DEEP_WARM_OCEAN,
                CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -1.05f,
                    -0.7f,
                )
            )

            BiomePlacement.addSubOverworld(
                Biomes.WARM_OCEAN,
                DEEP_WARM_OCEAN, CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -0.64f,
                    -0.455f,
                )
            )

            BiomePlacement.addSubOverworld(
                Biomes.WARM_OCEAN,
                DEEP_WARM_OCEAN, CriterionBuilder.value(
                    BiomeParameterTargets.CONTINENTALNESS,
                    -1.05f,
                    -0.7f,
                )
            )
        }
        //#endregion

        //#region Sulfuric Caves
        if (config.config.biomeConfig.generateSulfuricCave) {
            BiomePlacement.addSubOverworld(
                Biomes.DEEP_OCEAN,
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f,
                )
            )

            BiomePlacement.addSubOverworld(
                Biomes.DEEP_LUKEWARM_OCEAN,
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f
                )
            )

            BiomePlacement.addSubOverworld(
                DEEP_WARM_OCEAN,
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f,
                )
            )

            BiomePlacement.addSubOverworld(
                Biomes.DEEP_COLD_OCEAN,
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f,
                )
            )

            BiomePlacement.addSubOverworld(
                Biomes.DEEP_FROZEN_OCEAN,
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f,
                )
            )
        }
        //#endregion

        //#region Trenches
        BiomePlacement.addSubOverworld(
            Biomes.DEEP_OCEAN,
            TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            DEEP_CORAL_REEF,
            TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            TROPICAL_DEEP_CORAL_REEF,
            LUKEWARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN,
            LUKEWARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_COLD_OCEAN,
            COLD_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_FROZEN_OCEAN,
            FROZEN_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            DEEP_CORAL_REEF,
            FROZEN_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        //#region Warm Trench
        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN,
            WARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            SEAGRASS_BED,
            WARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            CORAL_REEF,
            WARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            RED_MEADOW,
            WARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )
        //#endregion

        //#region Still Life Compat
        // trenches
        BiomePlacement.addSubOverworld(
            ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath("still_life", "temperate_deep_ocean")
            ),
            TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath("still_life", "subtropical_deep_ocean")
            ),
            LUKEWARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )

        BiomePlacement.addSubOverworld(
            ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath("still_life", "tropical_deep_ocean")
            ),
            WARM_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f
            )
        )

        BiomePlacement.addSubOverworld(
            ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath("still_life", "cold_deep_ocean")
            ),
            COLD_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f
            )
        )

        BiomePlacement.addSubOverworld(
            ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath("still_life", "arctic_deep_ocean")
            ),
            FROZEN_TRENCH, CriterionBuilder.value(
                BiomeParameterTargets.CONTINENTALNESS,
                -0.72f,
                -0.62f,
            )
        )
        // deep coral reefs
        if (config.config.biomeConfig.generateDeepCoralReef) {
            BiomePlacement.replaceOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "temperate_deep_ocean")
                ),
                DEEP_CORAL_REEF,
                0.1
            )

            BiomePlacement.replaceOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "subtropical_deep_ocean")
                ),
                TROPICAL_DEEP_CORAL_REEF,
                0.1
            )

            BiomePlacement.replaceOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "cold_deep_ocean")
                ),
                DEEP_CORAL_REEF,
                0.1
            )

            BiomePlacement.replaceOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "arctic_deep_ocean")
                ),
                DEEP_CORAL_REEF,
                0.1
            )
        }

        // warm ocean biomes
        if (config.config.biomeConfig.generateSeagrassBed) {
            BiomePlacement.replaceOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "tropical_shallow_ocean")
                ),
                SEAGRASS_BED,
                0.25
            )
        }

        BiomePlacement.replaceOverworld(
            ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath("still_life", "tropical_shallow_ocean")
            ),
            CORAL_REEF,
            0.25
        )


        BiomePlacement.replaceOverworld(
            ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath("still_life", "tropical_shallow_ocean")
            ),
            RED_MEADOW,
            0.25
        )

        // sulfuric caves
        if (config.config.biomeConfig.generateSulfuricCave) {
            BiomePlacement.addSubOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "temperate_deep_ocean")
                ),
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f,
                )
            )

            BiomePlacement.addSubOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "subtropical_deep_ocean")
                ),
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f
                )
            )

            BiomePlacement.addSubOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "tropical_deep_ocean")
                ),
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f
                )
            )

            BiomePlacement.addSubOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "cold_deep_ocean")
                ),
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f
                )
            )

            BiomePlacement.addSubOverworld(
                ResourceKey.create(
                    Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath("still_life", "arctic_deep_ocean")
                ),
                SULFURIC_CAVES, CriterionBuilder.value(
                    BiomeParameterTargets.DEPTH,
                    0.2f,
                    0.5f,
                )
            )
        }
        //#endregion

        //#region Surface Rule Generation
        SurfaceGeneration.addOverworldSurfaceRules(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "rules/overworld"),
            ifTrue(
                abovePreliminarySurface(),
                sequence(
                    TIDE_POOL_SURFACE_RULE,
                    CORAL_REEF_SURFACE_RULE,

                    TRENCH_SURFACE_RULE,
                    LUKEWARM_TRENCH_SURFACE_RULE,
                    WARM_TRENCH_SURFACE_RULE,
                    COLD_TRENCH_SURFACE_RULE,
                    FROZEN_TRENCH_SURFACE_RULE,

                    SEAGRASS_BED_SURFACE_RULE,
                    RED_MEADOW_SURFACE_RULE,

                    TROPICAL_RIVER_SURFACE_RULE,

                    WARM_OCEAN_SURFACE_RULE,
                    LUKEWARM_OCEAN_SURFACE_RULE,
                    DEEP_WARM_OCEAN_SURFACE_RULE,

                    DEEP_CORAL_REEF_SURFACE_RULE,
                    TROPICAL_DEEP_CORAL_REEF_SURFACE_RULE,
                )
            )
        )
        //#endregion
    }
}
