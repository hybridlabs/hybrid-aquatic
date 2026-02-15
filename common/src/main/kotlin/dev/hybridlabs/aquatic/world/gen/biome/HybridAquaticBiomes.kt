package dev.hybridlabs.aquatic.world.gen.biome

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.SurfaceRules.*


object HybridAquaticBiomes {

    //#region Reworked Vanilla Surface Rules
    val WARM_OCEAN_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(Biomes.WARM_OCEAN),
        sequence(
            ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(HybridAquaticBlocks.SHORESTONE.get().defaultBlockState())),
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
            ifTrue(ON_FLOOR, state(HybridAquaticBlocks.SHORESTONE.get().defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState())),
        )
    )

    val BASALT_BEACH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("basalt_beach"))
    val BASALT_BEACH_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(BASALT_BEACH),
        sequence(
            ifTrue(ON_FLOOR, state(Blocks.BASALT.defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.BASALT.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SMOOTH_BASALT.defaultBlockState()))
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
            ifTrue(UNDER_FLOOR, state(HybridAquaticBlocks.SHORESTONE.get().defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
        )
    )

    val SEAGRASS_BED: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("seagrass_bed"))
    val SEAGRASS_BED_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(SEAGRASS_BED),
        sequence(
            ifTrue(ON_FLOOR, state(HybridAquaticBlocks.GRASSY_SAND.get().defaultBlockState())),
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
                state(HybridAquaticBlocks.WHITE_SAND.get().defaultBlockState())
            ),
            ifTrue(UNDER_FLOOR, state(HybridAquaticBlocks.WHITE_SAND.get().defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(HybridAquaticBlocks.WHITE_SANDSTONE.get().defaultBlockState()))
        )
    )

    val CORAL_REEF: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("coral_reef"))
    val CORAL_REEF_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(CORAL_REEF),
        sequence(
            ifTrue(
                ON_FLOOR,
                state(HybridAquaticBlocks.SHORESTONE.get().defaultBlockState())
            ),
            ifTrue(UNDER_FLOOR, state(Blocks.SMOOTH_SANDSTONE.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
        )
    )
    //#endregion

    //#region River Biome Surface Rules
    val PLACER_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("placer_river"))
    val PLACER_RIVER_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(PLACER_RIVER),
        sequence(
            ifTrue(ON_FLOOR, state(Blocks.RED_SAND.defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.CLAY.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TERRACOTTA.defaultBlockState()))
        )
    )

    val TROPICAL_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_river"))
    val TROPICAL_RIVER_SURFACE_RULE: RuleSource =
        ifTrue(isBiome(TROPICAL_RIVER), ifTrue(ON_FLOOR, state(Blocks.MUD.defaultBlockState())))

    val SEASONAL_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("seasonal_river"))
    val SEASONAL_RIVER_SURFACE_RULE: RuleSource =
        ifTrue(isBiome(SEASONAL_RIVER), ifTrue(ON_FLOOR, state(Blocks.COARSE_DIRT.defaultBlockState())))

    val COLD_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_river"))
    val COLD_RIVER_SURFACE_RULE: RuleSource =
        ifTrue(isBiome(COLD_RIVER), ifTrue(ON_FLOOR, state(Blocks.GRAVEL.defaultBlockState())))
    //#endregion

    //#region Trench Rules
    val BRINE_LAGOON: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("brine_lagoon"))
    val BRINE_LAGOON_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(BRINE_LAGOON),
        sequence(
            ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.MUD.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TUFF.defaultBlockState())),
        )
    )

    val TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("trench"))
    val TRENCH_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(
                TRENCH
            ),
            sequence(
                ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(Blocks.MUD.defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TUFF.defaultBlockState())),
            )
        )

    val WARM_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("warm_trench"))
    val WARM_TRENCH_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(WARM_TRENCH),
            sequence(
                ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(Blocks.MUD.defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TUFF.defaultBlockState())),
            )
        )

    val LUKEWARM_TRENCH: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("lukewarm_trench"))
    val LUKEWARM_TRENCH_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(LUKEWARM_TRENCH),
        sequence(
            ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.MUD.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TUFF.defaultBlockState())),
        )
    )

    val COLD_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_trench"))
    val COLD_TRENCH_SURFACE_RULE: RuleSource =
        ifTrue(
            isBiome(COLD_TRENCH),
            sequence(
                ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(Blocks.MUD.defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TUFF.defaultBlockState())),
            )
        )

    val FROZEN_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("frozen_trench"))
    val FROZEN_TRENCH_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(FROZEN_TRENCH),
        sequence(
            ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.MUD.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TUFF.defaultBlockState())),
        )
    )

    val SULFURIC_CAVES: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("sulfuric_caves"))

    val VOLCANIC_TRENCH: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("volcanic_trench"))
    val VOLCANIC_TRENCH_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(VOLCANIC_TRENCH),
        sequence(
            ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())),
            ifTrue(UNDER_FLOOR, state(Blocks.MUD.defaultBlockState())),
            ifTrue(DEEP_UNDER_FLOOR, state(Blocks.TUFF.defaultBlockState())),
        )
    )

    //#endregion

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
                ifTrue(ON_FLOOR, state(HybridAquaticBlocks.CORALSTONE.get().defaultBlockState()))
            )
        )

    fun addBiomes() {

        //#region River Generation Fixes
        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.DEEP_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.COLD_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.COLD_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.DEEP_COLD_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.COLD_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.FROZEN_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.FROZEN_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.FROZEN_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.DEEP_FROZEN_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.LUKEWARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.LUKEWARM_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.LUKEWARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.DEEP_LUKEWARM_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            Biomes.WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    Biomes.WARM_OCEAN,
                    false
                )
            )
        )
        //#endregion

        //#region New Rivers
        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            PLACER_RIVER,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    BiomeTags.IS_BADLANDS,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            TROPICAL_RIVER,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    BiomeTags.IS_JUNGLE,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            SEASONAL_RIVER,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    BiomeTags.IS_SAVANNA,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER,
            COLD_RIVER,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    BiomeTags.IS_TAIGA,
                    false
                )
            )
        )
        //#endregion

        //#region Beach Biomes
        BiomePlacement.addSubOverworld(
            Biomes.STONY_SHORE,
            BASALT_BEACH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    BiomeTags.IS_OCEAN,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.BEACH,
            TIDE_POOLS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.EDGE,
                    SubBiomeMatcher.CriterionTypes.RATIO,
                    0.0f,
                    0.5f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofBiome(
                    SubBiomeMatcher.CriterionTargets.NEIGHBOR,
                    HybridAquaticBiomeTags.TROPICAL_OCEANS,
                    false
                )
            )
        )
        //#endregion

        //#region Warm Ocean Biomes
        BiomePlacement.replaceOverworld(
            Biomes.WARM_OCEAN,
            SEAGRASS_BED,
            0.25
        )

        BiomePlacement.replaceOverworld(
            Biomes.WARM_OCEAN,
            CORAL_REEF,
            0.25
        )

        BiomePlacement.replaceOverworld(
            Biomes.WARM_OCEAN,
            RED_MEADOW,
            0.25
        )

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
        //#endregion

        //#region Deep Warm Ocean
        BiomePlacement.addSubOverworld(
            CORAL_REEF,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.64f,
                    -0.455f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            CORAL_REEF,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -1.05f,
                    -0.7f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            SEAGRASS_BED,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.64f,
                    -0.455f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            SEAGRASS_BED,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -1.05f,
                    -0.7f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            RED_MEADOW,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.64f,
                    -0.455f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            RED_MEADOW,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -1.05f,
                    -0.7f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.64f,
                    -0.455f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN,
            DEEP_WARM_OCEAN,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -1.05f,
                    -0.7f,
                    false
                )
            )
        )
        //#endregion

        //#region Sulfuric Caves
        BiomePlacement.addSubOverworld(
            Biomes.DEEP_OCEAN,
            SULFURIC_CAVES,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.2f,
                    0.5f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN,
            SULFURIC_CAVES,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.1f,
                    0.6f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            DEEP_WARM_OCEAN,
            SULFURIC_CAVES,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.1f,
                    0.6f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_COLD_OCEAN,
            SULFURIC_CAVES,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.2f,
                    0.5f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_FROZEN_OCEAN,
            SULFURIC_CAVES,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.2f,
                    0.5f,
                    false
                )
            )
        )
        //#endregion

        //#region Trenches
        BiomePlacement.addSubOverworld(
            Biomes.DEEP_OCEAN,
            TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            DEEP_CORAL_REEF,
            TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            TROPICAL_DEEP_CORAL_REEF,
            LUKEWARM_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN,
            LUKEWARM_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_COLD_OCEAN,
            COLD_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_FROZEN_OCEAN,
            FROZEN_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            DEEP_CORAL_REEF,
            FROZEN_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        //#region Warm Trench
        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN,
            WARM_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            SEAGRASS_BED,
            WARM_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            CORAL_REEF,
            WARM_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            RED_MEADOW,
            WARM_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    0.33f,
                    false
                )
            )
        )
        //#endregion

        //#region Volcanic Trench
        BiomePlacement.addSubOverworld(
            Biomes.DEEP_OCEAN,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            DEEP_CORAL_REEF,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            TROPICAL_DEEP_CORAL_REEF,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            CORAL_REEF,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            SEAGRASS_BED,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            RED_MEADOW,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_COLD_OCEAN,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_FROZEN_OCEAN,
            VOLCANIC_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f,
                    1.0f,
                    false
                )
            )
        )
        //#endregion

        //#region Brine Lagoon
        BiomePlacement.addSubOverworld(
            Biomes.DEEP_OCEAN,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            DEEP_CORAL_REEF,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            TROPICAL_DEEP_CORAL_REEF,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            CORAL_REEF,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            SEAGRASS_BED,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            RED_MEADOW,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_COLD_OCEAN,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_FROZEN_OCEAN,
            BRINE_LAGOON,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.7f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.WEIRDNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.33f,
                    -1.0f,
                    false
                )
            )
        )
        //#endregion

        //#endregion

        //#region Surface Rule Generation
        SurfaceGeneration.addOverworldSurfaceRules(
            ResourceLocation("minecraft", "rules/overworld"),
            ifTrue(
                abovePreliminarySurface(),
                sequence(
                    BASALT_BEACH_SURFACE_RULE,
                    TIDE_POOL_SURFACE_RULE,
                    CORAL_REEF_SURFACE_RULE,

                    BRINE_LAGOON_SURFACE_RULE,
                    VOLCANIC_TRENCH_SURFACE_RULE,
                    TRENCH_SURFACE_RULE,
                    LUKEWARM_TRENCH_SURFACE_RULE,
                    WARM_TRENCH_SURFACE_RULE,
                    COLD_TRENCH_SURFACE_RULE,
                    FROZEN_TRENCH_SURFACE_RULE,
                    SEAGRASS_BED_SURFACE_RULE,
                    RED_MEADOW_SURFACE_RULE,

                    PLACER_RIVER_SURFACE_RULE,
                    TROPICAL_RIVER_SURFACE_RULE,
                    SEASONAL_RIVER_SURFACE_RULE,
                    COLD_RIVER_SURFACE_RULE,

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