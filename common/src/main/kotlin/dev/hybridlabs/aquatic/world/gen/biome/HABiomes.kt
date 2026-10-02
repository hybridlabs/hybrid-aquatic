package dev.hybridlabs.aquatic.world.gen.biome

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.config.ConfigHelper
import dev.hybridlabs.aquatic.world.gen.OverworldGenerator
import dev.hybridlabs.aquatic.world.gen.biome.HABiomePlacement.Criterion.Continentalness
import dev.hybridlabs.aquatic.world.gen.biome.HABiomePlacement.Criterion.Depth
import dev.hybridlabs.aquatic.world.gen.biome.HABiomePlacement.Criterion.Edge
import dev.hybridlabs.aquatic.world.gen.biome.HABiomePlacement.Criterion.Neighbor
import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.SurfaceRules.*

object HABiomes {
    val config by lazy { ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE) }

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

    //#region Still Life Compat
    private val STILL_LIFE_TROPICAL_SHALLOW_OCEAN = stillLife("tropical_shallow_ocean")
    private val STILL_LIFE_TROPICAL_DEEP_OCEAN = stillLife("tropical_deep_ocean")
    private val STILL_LIFE_SUBTROPICAL_DEEP_OCEAN = stillLife("subtropical_deep_ocean")
    private val STILL_LIFE_TEMPERATE_DEEP_OCEAN = stillLife("temperate_deep_ocean")
    private val STILL_LIFE_COLD_DEEP_OCEAN = stillLife("cold_deep_ocean")
    private val STILL_LIFE_ARCTIC_DEEP_OCEAN = stillLife("arctic_deep_ocean")

    private fun stillLife(name: String): ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, ResourceLocation("still_life", name))
    //#endregion

    fun addBiomes(placement: HABiomePlacement.Builder) {
        //#region River Generation Fixes
        placement.addSubOverworld(Biomes.RIVER, Biomes.OCEAN, Neighbor(Biomes.OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.OCEAN, Neighbor(Biomes.DEEP_OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.COLD_OCEAN, Neighbor(Biomes.COLD_OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.DEEP_COLD_OCEAN, Neighbor(Biomes.COLD_OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.FROZEN_OCEAN, Neighbor(Biomes.FROZEN_OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.FROZEN_OCEAN, Neighbor(Biomes.DEEP_FROZEN_OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.LUKEWARM_OCEAN, Neighbor(Biomes.LUKEWARM_OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.LUKEWARM_OCEAN, Neighbor(Biomes.DEEP_LUKEWARM_OCEAN))
        placement.addSubOverworld(Biomes.RIVER, Biomes.WARM_OCEAN, Neighbor(Biomes.WARM_OCEAN))
        //#endregion

        //#region New Rivers
        if (config.config.biomeConfig.generateTropicalRiver) {
            placement.addSubOverworld(Biomes.RIVER, TROPICAL_RIVER, Neighbor(BiomeTags.IS_JUNGLE))
        }
        //#endregion

        //#region Beach Biomes
        if (config.config.biomeConfig.generateTidePools) {
            placement.addSubOverworld(Biomes.BEACH, TIDE_POOLS, Edge(0.0f, 0.5f), Neighbor(HAPIBiomeTags.LUKEWARM_OCEANS))
        }
        //#endregion

        //#region Warm Ocean Biomes
        if (config.config.biomeConfig.generateSeagrassBed) {
            placement.replaceOverworld(Biomes.WARM_OCEAN, SEAGRASS_BED, 0.25)
        }

        placement.replaceOverworld(Biomes.WARM_OCEAN, CORAL_REEF, 0.25)
        placement.replaceOverworld(Biomes.WARM_OCEAN, RED_MEADOW, 0.25)

        if (config.config.biomeConfig.generateDeepCoralReef) {
            placement.replaceOverworld(Biomes.DEEP_OCEAN, DEEP_CORAL_REEF, 0.1)
            placement.replaceOverworld(Biomes.DEEP_LUKEWARM_OCEAN, TROPICAL_DEEP_CORAL_REEF, 0.1)
            placement.replaceOverworld(Biomes.DEEP_COLD_OCEAN, DEEP_CORAL_REEF, 0.1)
            placement.replaceOverworld(Biomes.DEEP_FROZEN_OCEAN, DEEP_CORAL_REEF, 0.1)
        }
        //#endregion

        //#region Deep Warm Ocean
        if (config.config.biomeConfig.generateDeepWarmOcean) {
            for (target in listOf(CORAL_REEF, SEAGRASS_BED, RED_MEADOW, Biomes.WARM_OCEAN)) {
                placement.addSubOverworld(target, DEEP_WARM_OCEAN, Continentalness(-0.64f, -0.455f))
                placement.addSubOverworld(target, DEEP_WARM_OCEAN, Continentalness(-1.05f, -0.7f))
            }
        }
        //#endregion

        //#region Sulfuric Caves
        if (config.config.biomeConfig.generateSulfuricCave) {
            placement.addSubOverworld(Biomes.DEEP_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(Biomes.DEEP_LUKEWARM_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(DEEP_WARM_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(Biomes.DEEP_COLD_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(Biomes.DEEP_FROZEN_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
        }
        //#endregion

        //#region Trenches
        placement.addSubOverworld(Biomes.DEEP_OCEAN, TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(DEEP_CORAL_REEF, TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(TROPICAL_DEEP_CORAL_REEF, LUKEWARM_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(Biomes.DEEP_LUKEWARM_OCEAN, LUKEWARM_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(Biomes.DEEP_COLD_OCEAN, COLD_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(Biomes.DEEP_FROZEN_OCEAN, FROZEN_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(DEEP_CORAL_REEF, FROZEN_TRENCH, Continentalness(-0.72f, -0.62f))
        //#endregion

        //#region Warm Trench
        placement.addSubOverworld(Biomes.WARM_OCEAN, WARM_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(SEAGRASS_BED, WARM_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(CORAL_REEF, WARM_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(RED_MEADOW, WARM_TRENCH, Continentalness(-0.72f, -0.62f))
        //#endregion

        //#region Still Life Compat
        // trenches
        placement.addSubOverworld(STILL_LIFE_TEMPERATE_DEEP_OCEAN, TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(STILL_LIFE_SUBTROPICAL_DEEP_OCEAN, LUKEWARM_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(STILL_LIFE_TROPICAL_DEEP_OCEAN, WARM_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(STILL_LIFE_COLD_DEEP_OCEAN, COLD_TRENCH, Continentalness(-0.72f, -0.62f))
        placement.addSubOverworld(STILL_LIFE_ARCTIC_DEEP_OCEAN, FROZEN_TRENCH, Continentalness(-0.72f, -0.62f))

        // deep coral reefs
        if (config.config.biomeConfig.generateDeepCoralReef) {
            placement.replaceOverworld(STILL_LIFE_TEMPERATE_DEEP_OCEAN, DEEP_CORAL_REEF, 0.1)
            placement.replaceOverworld(STILL_LIFE_SUBTROPICAL_DEEP_OCEAN, TROPICAL_DEEP_CORAL_REEF, 0.1)
            placement.replaceOverworld(STILL_LIFE_COLD_DEEP_OCEAN, DEEP_CORAL_REEF, 0.1)
            placement.replaceOverworld(STILL_LIFE_ARCTIC_DEEP_OCEAN, DEEP_CORAL_REEF, 0.1)
        }

        // warm ocean biomes
        if (config.config.biomeConfig.generateSeagrassBed) {
            placement.replaceOverworld(STILL_LIFE_TROPICAL_SHALLOW_OCEAN, SEAGRASS_BED, 0.25)
        }

        placement.replaceOverworld(STILL_LIFE_TROPICAL_SHALLOW_OCEAN, CORAL_REEF, 0.25)
        placement.replaceOverworld(STILL_LIFE_TROPICAL_SHALLOW_OCEAN, RED_MEADOW, 0.25)

        // sulfuric caves
        if (config.config.biomeConfig.generateSulfuricCave) {
            placement.addSubOverworld(STILL_LIFE_TEMPERATE_DEEP_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(STILL_LIFE_SUBTROPICAL_DEEP_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(STILL_LIFE_TROPICAL_DEEP_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(STILL_LIFE_COLD_DEEP_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
            placement.addSubOverworld(STILL_LIFE_ARCTIC_DEEP_OCEAN, SULFURIC_CAVES, Depth(0.2f, 0.5f))
        }
        //#endregion
    }

    /**
     * Prepends the surface rules above to the overworld's before the server loads its levels, so they run ahead of the
     * vanilla rules.
     */
    @JvmStatic
    fun applySurfaceRules(server: MinecraftServer) {
        if (!config.config.biomeConfig.enableBiomes) return

        val generator = OverworldGenerator.find(server) ?: return
        val settings = OverworldGenerator.registeredSettings(generator) ?: run {
            Constants.LOGGER.warn("Overworld noise settings are not registered, so biome surfaces will not be changed")
            return
        }

        val surfaceRule = ifTrue(
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

        OverworldGenerator.rebindSettings(settings, surfaceRule = sequence(surfaceRule, settings.value().surfaceRule()))
    }
}
