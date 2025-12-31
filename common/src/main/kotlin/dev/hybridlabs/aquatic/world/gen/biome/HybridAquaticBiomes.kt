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

    val ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("abyssal_plains"))
    val ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(isBiome(ABYSSAL_PLAINS), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val LUKEWARM_ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("lukewarm_abyssal_plains"))
    val LUKEWARM_ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(isBiome(LUKEWARM_ABYSSAL_PLAINS), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val COLD_ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_abyssal_plains"))
    val COLD_ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(isBiome(COLD_ABYSSAL_PLAINS), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val FROZEN_ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("frozen_abyssal_plains"))
    val FROZEN_ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(isBiome(FROZEN_ABYSSAL_PLAINS), ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState())))

    val THERMAL_VENT_CAVERNS: ResourceKey<Biome?> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("thermal_vent_caverns"))

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
            THERMAL_VENT_CAVERNS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.2f,
                    0.9f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN,
            THERMAL_VENT_CAVERNS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.0f,
                    0.5f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_COLD_OCEAN,
            THERMAL_VENT_CAVERNS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.2f,
                    0.9f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_FROZEN_OCEAN,
            THERMAL_VENT_CAVERNS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.DEPTH,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    0.2f,
                    0.9f,
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
            ABYSSAL_PLAINS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.70f,
                    -0.64f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN,
            LUKEWARM_ABYSSAL_PLAINS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.70f,
                    -0.64f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_COLD_OCEAN,
            COLD_ABYSSAL_PLAINS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.70f,
                    -0.64f,
                    false
                )
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.DEEP_FROZEN_OCEAN,
            FROZEN_ABYSSAL_PLAINS,
            SubBiomeMatcher.of(
                SubBiomeMatcher.Criterion.ofRange(
                    SubBiomeMatcher.CriterionTargets.CONTINENTALNESS,
                    SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.70f,
                    -0.64f,
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
                    ABYSSAL_PLAINS_SURFACE_RULE,
                    LUKEWARM_ABYSSAL_PLAINS_SURFACE_RULE,
                    COLD_ABYSSAL_PLAINS_SURFACE_RULE,
                    FROZEN_ABYSSAL_PLAINS_SURFACE_RULE,
                    TROPICAL_RIVER_SURFACE_RULE,
                )
            )
        )
    }
}