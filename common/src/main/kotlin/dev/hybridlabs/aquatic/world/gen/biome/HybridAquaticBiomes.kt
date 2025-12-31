package dev.hybridlabs.aquatic.world.gen.biome

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.Climate.Parameter
import net.minecraft.world.level.biome.Climate.ParameterPoint
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.SurfaceRules.*

object HybridAquaticBiomes {
    val TIDE_POOLS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tide_pools"))
    val TIDE_POOL_SURFACE_RULE: RuleSource = ifTrue(isBiome(TIDE_POOLS), state(Blocks.SAND.defaultBlockState()))

    val TROPICAL_RIVER: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_river"))

    val ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("abyssal_plains"))
    val LUKEWARM_ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("lukewarm_abyssal_plains"))
    val COLD_ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_abyssal_plains"))
    val FROZEN_ABYSSAL_PLAINS: ResourceKey<Biome?> = ResourceKey.create(Registries.BIOME, CommonClass.locate("frozen_abyssal_plains"))
    val ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(ABYSSAL_PLAINS),
        ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState()))
    )
    val LUKEWARM_ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(LUKEWARM_ABYSSAL_PLAINS),
        ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState()))
    )
    val COLD_ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(COLD_ABYSSAL_PLAINS),
        ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState()))
    )
    val FROZEN_ABYSSAL_PLAINS_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(FROZEN_ABYSSAL_PLAINS),
        ifTrue(ON_FLOOR, state(HybridAquaticBlocks.MARINE_SNOW.get().defaultBlockState()))
    )

    val TROPICAL_RIVER_SURFACE_RULE: RuleSource = ifTrue(
        isBiome(TROPICAL_RIVER),
        ifTrue(ON_FLOOR, state(Blocks.MUD.defaultBlockState()))
    )

    fun addBiomes() {
        BiomePlacement.addOverworld(
            TIDE_POOLS, ParameterPoint(
                Parameter.span(-0.45f, 1f),
                Parameter.span(-0.45f, -0.15f),
                Parameter.span(-0.195f, -0.110f),
                Parameter.span(-0.25f, 0.05f),
                Parameter.point(0f),
                Parameter.span(-0.267f, 0.05f),
                0
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