package dev.hybridlabs.aquatic.world.gen.biome

import com.terraformersmc.biolith.api.biome.BiomePlacement
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.config.ConfigHelper
import dev.hybridlabs.aquatic.world.gen.biome.HABiomeInjectors.BiomeEnabledPredicate
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes.surfaceRule
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import dev.worldgen.lithostitched.api.util.InjectionType
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.SurfaceRules.DEEP_UNDER_FLOOR
import net.minecraft.world.level.levelgen.SurfaceRules.ON_FLOOR
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource
import net.minecraft.world.level.levelgen.SurfaceRules.UNDER_FLOOR
import net.minecraft.world.level.levelgen.SurfaceRules.abovePreliminarySurface
import net.minecraft.world.level.levelgen.SurfaceRules.ifTrue
import net.minecraft.world.level.levelgen.SurfaceRules.isBiome
import net.minecraft.world.level.levelgen.SurfaceRules.sequence
import net.minecraft.world.level.levelgen.SurfaceRules.state

object HABiomes {
    val config = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)

    val TIDE_POOLS: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tide_pools"))
    val DEEP_WARM_OCEAN: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("deep_warm_ocean"))
    val SEAGRASS_BED: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("seagrass_bed"))
    val RED_MEADOW: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("red_meadow"))
    val CORAL_REEF: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("coral_reef"))
    val TROPICAL_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_river"))
    val TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("trench"))
    val WARM_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("warm_trench"))
    val LUKEWARM_TRENCH: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("lukewarm_trench"))
    val COLD_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_trench"))
    val FROZEN_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("frozen_trench"))
    val SULFURIC_CAVES: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("sulfuric_caves"))
    val DEEP_CORAL_REEF: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("deep_coral_reef"))
    val TROPICAL_DEEP_CORAL_REEF: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_deep_coral_reef"))

    /**
     * Registers each biome's surface rule as a Lithostitched `add_surface_rule` worldgen modifier named
     * `surface_rule/<biome>`. Each rule only applies inside its biome and above the preliminary surface, and is
     * prepended so it runs before the vanilla rules. To give a biome a surface, add a [surfaceRule] call here.
     */
    fun bootstrapSurfaceRules(context: BootstrapContext<WorldgenModifier>) {
        //#region Reworked Vanilla Surface Rules
        context.surfaceRule(
            Biomes.WARM_OCEAN,
            sequence(
                ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
            )
        )

        context.surfaceRule(
            Biomes.LUKEWARM_OCEAN,
            sequence(
                ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SMOOTH_SANDSTONE.defaultBlockState()))
            )
        )
        //#endregion

        //#region Beach Biome Surface Rules
        context.surfaceRule(
            TIDE_POOLS,
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState())),
            )
        )
        //#endregion

        //#region Warm Ocean Biome Surface Rules
        context.surfaceRule(
            DEEP_WARM_OCEAN,
            sequence(
                ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
            )
        )

        context.surfaceRule(
            SEAGRASS_BED,
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.GRASSY_SAND.get().defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(Blocks.SAND.defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
            )
        )

        context.surfaceRule(
            RED_MEADOW,
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.WHITE_SAND.get().defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(HABlocks.WHITE_SAND.get().defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(HABlocks.WHITE_SANDSTONE.get().defaultBlockState()))
            )
        )

        context.surfaceRule(
            CORAL_REEF,
            sequence(
                ifTrue(ON_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                ifTrue(UNDER_FLOOR, state(Blocks.SMOOTH_SANDSTONE.defaultBlockState())),
                ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
            )
        )
        //#endregion

        //#region River Biome Surface Rules
        context.surfaceRule(TROPICAL_RIVER, ifTrue(ON_FLOOR, state(Blocks.MUD.defaultBlockState())))
        //#endregion

        //#region Trench Rules
        for (trench in listOf(TRENCH, WARM_TRENCH, LUKEWARM_TRENCH, COLD_TRENCH, FROZEN_TRENCH)) {
            context.surfaceRule(
                trench,
                sequence(
                    ifTrue(ON_FLOOR, state(HABlocks.MARINE_SNOW.get().defaultBlockState())),
                    state(HABlocks.SCHIST.get().defaultBlockState())
                )
            )
        }
        //#endregion

        //#region Deep Reefs
        context.surfaceRule(DEEP_CORAL_REEF, ifTrue(ON_FLOOR, state(Blocks.TUFF.defaultBlockState())))
        context.surfaceRule(
            TROPICAL_DEEP_CORAL_REEF,
            ifTrue(ON_FLOOR, state(HABlocks.CORALSTONE.get().defaultBlockState()))
        )
        //#endregion
    }

    private fun BootstrapContext<WorldgenModifier>.surfaceRule(biome: ResourceKey<Biome>, rule: RuleSource) {
        register(
            ResourceKey.create(
                LithostitchedRegistries.WORLDGEN_MODIFIER,
                CommonClass.locate("surface_rule/${biome.location().path}")
            ),
            WorldgenModifier.builder(BiomeEnabledPredicate(biome)).addSurfaceRule(
                Level.OVERWORLD,
                InjectionType.PREPEND,
                ifTrue(abovePreliminarySurface(), ifTrue(isBiome(biome), rule))
            )
        )
    }


    fun addBiomes() {
        //#region Warm Ocean Biomes
        if (config.config.biomeConfig.generateSeagrassBed) {
            BiomePlacement.replaceOverworld(
                Biomes.WARM_OCEAN,
                SEAGRASS_BED,
                0.25
            )
        }


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
    }
}
