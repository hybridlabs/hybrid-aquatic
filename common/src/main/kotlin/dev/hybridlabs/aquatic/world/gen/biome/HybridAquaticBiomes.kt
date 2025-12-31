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
    val TIDE_POOLS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tide_pools"))
    val TIDE_POOL_SURFACE_RULE: RuleSource = ifTrue(isBiome(TIDE_POOLS), state(Blocks.SAND.defaultBlockState()))

    val TROPICAL_RIVER: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_river"))
    val TROPICAL_RIVER_SURFACE_RULE: RuleSource = ifTrue(isBiome(TROPICAL_RIVER), ifTrue(ON_FLOOR, state(Blocks.MUD.defaultBlockState())))

    val TRENCH: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("trench"))
    val TRENCH_SURFACE_RULE: RuleSource = ifTrue(isBiome(TRENCH), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val WARM_TRENCH: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("warm_trench"))
    val WARM_TRENCH_SURFACE_RULE: RuleSource = ifTrue(isBiome(WARM_TRENCH), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val LUKEWARM_TRENCH: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("lukewarm_trench"))
    val LUKEWARM_TRENCH_SURFACE_RULE: RuleSource = ifTrue(isBiome(LUKEWARM_TRENCH), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val COLD_TRENCH: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_trench"))
    val COLD_TRENCH_SURFACE_RULE: RuleSource = ifTrue(isBiome(COLD_TRENCH), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val FROZEN_TRENCH: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("frozen_trench"))
    val FROZEN_TRENCH_SURFACE_RULE: RuleSource = ifTrue(isBiome(FROZEN_TRENCH), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val SULFURIC_CAVES: ResourceKey<Biome?> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("sulfuric_caves"))

    fun addBiomes() {
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
            Biomes.DEEP_OCEAN,
            TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.70f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.4f,
                    0.5f,
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
                    -0.70f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.4f,
                    0.5f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN,
            WARM_TRENCH,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.70f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.4f,
                    0.5f,
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
                    -0.70f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.4f,
                    0.5f,
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
                    -0.70f,
                    -0.64f,
                    false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.4f,
                    0.5f,
                    false
                )
            )
        )

        SurfaceGeneration.addOverworldSurfaceRules(
            ResourceLocation("minecraft", "rules/overworld"),
            ifTrue(
                abovePreliminarySurface(),
                sequence(
                    TIDE_POOL_SURFACE_RULE,
                    TRENCH_SURFACE_RULE,
                    WARM_TRENCH_SURFACE_RULE,
                    LUKEWARM_TRENCH_SURFACE_RULE,
                    COLD_TRENCH_SURFACE_RULE,
                    FROZEN_TRENCH_SURFACE_RULE,
                    TROPICAL_RIVER_SURFACE_RULE,
                )
            )
        )
    }
}