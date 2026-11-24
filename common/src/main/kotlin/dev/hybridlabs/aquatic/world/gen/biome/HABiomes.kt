package dev.hybridlabs.aquatic.world.gen.biome

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.config.HAConfig
import dev.hybridlabs.aquatic.config.HAConfigHolder
import dev.hybridlabs.hapi.config.ConfigHandler
import dev.hybridlabs.aquatic.world.gen.biome.HABiomeInjectors.BiomeEnabledPredicate
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import dev.worldgen.lithostitched.api.util.InjectionType
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.material.OverworldMaterialRules
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.material.MaterialRules
import net.minecraft.world.level.levelgen.material.MaterialRules.abovePreliminarySurface
import net.minecraft.world.level.levelgen.material.MaterialRules.ifTrue
import net.minecraft.world.level.levelgen.material.MaterialRules.isBiome
import net.minecraft.world.level.levelgen.material.MaterialRules.sequence
import net.minecraft.world.level.levelgen.material.MaterialRules.state
import net.minecraft.world.level.levelgen.material.MaterialRules.stoneDepthCheck
import net.minecraft.world.level.levelgen.material.rule.MaterialRule
import net.minecraft.world.level.levelgen.placement.CaveSurface

object HABiomes {
    val config: ConfigHandler<HAConfig>
        get() = HAConfigHolder.handler

    val TIDE_POOLS: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tide_pools"))
    val DEEP_WARM_OCEAN: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("deep_warm_ocean"))
    val SEAGRASS_BED: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("seagrass_bed"))
    val RED_MEADOW: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("red_meadow"))
    val CORAL_REEF: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("coral_reef"))

    val TROPICAL_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_river"))
    val COLD_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_river"))
    val BLACKWATER_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("blackwater_river"))
    val FORESTED_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("forested_river"))
    val FLORAL_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("floral_river"))
    val EXOTIC_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("exotic_river"))
    val EXOTIC_DESERT_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("exotic_desert_river"))
    val EXOTIC_BADLANDS_RIVER: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("exotic_badlands_river"))

    val LUSH_DESERT: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("lush_desert"))

    val ESTUARY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("estuary"))
    val COLD_ESTUARY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_estuary"))
    val TROPICAL_ESTUARY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_estuary"))
    val EXOTIC_ESTUARY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("exotic_estuary"))
    val EXOTIC_DESERT_ESTUARY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("exotic_desert_estuary"))
    val EXOTIC_BADLANDS_ESTUARY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("exotic_badlands_estuary"))
    val BLACKWATER_ESTUARY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("blackwater_estuary"))

    val TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("trench"))
    val WARM_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("warm_trench"))
    val LUKEWARM_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("lukewarm_trench"))
    val COLD_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("cold_trench"))
    val FROZEN_TRENCH: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("frozen_trench"))
    val SULFURIC_CAVES: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("sulfuric_caves"))
    val DEEP_CORAL_REEF: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("deep_coral_reef"))
    val TROPICAL_DEEP_CORAL_REEF: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, CommonClass.locate("tropical_deep_coral_reef"))

    private val ON_FLOOR = stoneDepthCheck(0, false, CaveSurface.FLOOR)
    private val UNDER_FLOOR = stoneDepthCheck(0, true, CaveSurface.FLOOR)
    private val DEEP_UNDER_FLOOR = stoneDepthCheck(0, true, 6, CaveSurface.FLOOR)

    private data class BiomeSurfaceRule(val biome: ResourceKey<Biome>, val rule: MaterialRule)

    private fun biomeSurfaceRules(): List<BiomeSurfaceRule> = buildList {
        //#region Reworked Vanilla Surface Rules
        add(
            BiomeSurfaceRule(
                Biomes.WARM_OCEAN,
                sequence(
                    ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                    ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
                )
            )
        )

        add(
            BiomeSurfaceRule(
                Biomes.LUKEWARM_OCEAN,
                sequence(
                    ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState())),
                    ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SMOOTH_SANDSTONE.defaultBlockState()))
                )
            )
        )
        //#endregion

        //#region Beach Biome Surface Rules
        add(
            BiomeSurfaceRule(
                TIDE_POOLS,
                sequence(
                    ifTrue(ON_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState())),
                )
            )
        )
        //#endregion

        //#region Warm Ocean Biome Surface Rules
        add(
            BiomeSurfaceRule(
                DEEP_WARM_OCEAN,
                sequence(
                    ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                    ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
                )
            )
        )

        add(
            BiomeSurfaceRule(
                SEAGRASS_BED,
                sequence(
                    ifTrue(ON_FLOOR, state(HABlocks.GRASSY_SAND.get().defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.SAND.defaultBlockState())),
                    ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
                )
            )
        )

        add(
            BiomeSurfaceRule(
                RED_MEADOW,
                sequence(
                    ifTrue(ON_FLOOR, state(HABlocks.WHITE_SAND.get().defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(HABlocks.WHITE_SAND.get().defaultBlockState())),
                    ifTrue(DEEP_UNDER_FLOOR, state(HABlocks.WHITE_SANDSTONE.get().defaultBlockState()))
                )
            )
        )

        add(
            BiomeSurfaceRule(
                CORAL_REEF,
                sequence(
                    ifTrue(ON_FLOOR, state(HABlocks.SHORESTONE.get().defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.SMOOTH_SANDSTONE.defaultBlockState())),
                    ifTrue(DEEP_UNDER_FLOOR, state(Blocks.SANDSTONE.defaultBlockState()))
                )
            )
        )
        //#endregion

        //#region River Biome Surface Rules
        add(BiomeSurfaceRule(TROPICAL_RIVER, ifTrue(ON_FLOOR, state(Blocks.MUD.defaultBlockState()))))
        add(BiomeSurfaceRule(COLD_RIVER, ifTrue(ON_FLOOR, state(Blocks.GRAVEL.defaultBlockState()))))

        add(
            BiomeSurfaceRule(
                EXOTIC_RIVER,
                sequence(
                    ifTrue(ON_FLOOR, state(Blocks.MUD.defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.CLAY.defaultBlockState())),
                )
            )
        )

        add(
            BiomeSurfaceRule(
                EXOTIC_DESERT_RIVER,
                sequence(
                    ifTrue(ON_FLOOR, state(Blocks.SAND.defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.CLAY.defaultBlockState())),
                )
            )
        )

        add(
            BiomeSurfaceRule(
                LUSH_DESERT,
                sequence(
                    ifTrue(ON_FLOOR, state(Blocks.GRASS_BLOCK.defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.COARSE_DIRT.defaultBlockState())),
                )
            )
        )

        add(
            BiomeSurfaceRule(
                EXOTIC_BADLANDS_RIVER,
                sequence(
                    ifTrue(ON_FLOOR, state(Blocks.RED_SAND.defaultBlockState())),
                    ifTrue(UNDER_FLOOR, state(Blocks.CLAY.defaultBlockState())),
                )
            )
        )
        //#endregion

        //#region Trench Rules
        for (trench in listOf(TRENCH, WARM_TRENCH, LUKEWARM_TRENCH, COLD_TRENCH, FROZEN_TRENCH)) {
            add(
                BiomeSurfaceRule(
                    trench,
                    sequence(
                        ifTrue(ON_FLOOR, state(HABlocks.MARINE_SNOW.get().defaultBlockState())),
                        state(HABlocks.SCHIST.get().defaultBlockState())
                    )
                )
            )
        }
        //#endregion

        //#region Deep Reefs
        add(BiomeSurfaceRule(DEEP_CORAL_REEF, ifTrue(ON_FLOOR, state(Blocks.TUFF.defaultBlockState()))))
        add(
            BiomeSurfaceRule(
                TROPICAL_DEEP_CORAL_REEF,
                ifTrue(ON_FLOOR, state(HABlocks.CORALSTONE.get().defaultBlockState()))
            )
        )
        //#endregion
    }

    /**
     * Registers each biome's surface rule as a named material rule.
     */
    fun bootstrapMaterialRules(context: BootstrapContext<MaterialRule>) {
        for (entry in biomeSurfaceRules()) {
            context.register(materialRuleKey(entry.biome), entry.rule)
        }
    }

    /**
     * Registers each biome's surface rule as a Lithostitched `set_material_rule` worldgen modifier named
     * `surface_rule/<biome>`. Each rule only applies inside its biome and above the preliminary surface, and is
     * prepended to the overworld material rule so it runs before the vanilla rules. To give a biome a surface,
     * add an entry to [biomeSurfaceRules].
     */
    fun bootstrapSurfaceRules(context: BootstrapContext<WorldgenModifier>) {
        val materialRules = context.lookup(Registries.MATERIAL_RULE)
        val biomes = context.lookup(Registries.BIOME)
        val overworld: Holder<MaterialRule> = materialRules.getOrThrow(OverworldMaterialRules.OVERWORLD)

        for (entry in biomeSurfaceRules()) {
            val rule = MaterialRules.getRule(materialRules, materialRuleKey(entry.biome))
            val wrapped = ifTrue(
                abovePreliminarySurface(),
                ifTrue(isBiome(biomes, entry.biome), rule)
            )

            context.register(
                ResourceKey.create(
                    LithostitchedRegistries.WORLDGEN_MODIFIER,
                    CommonClass.locate("surface_rule/${entry.biome.identifier().path}")
                ),
                WorldgenModifier.builder(BiomeEnabledPredicate(entry.biome))
                    .setMaterialRule(overworld, Holder.direct(wrapped), InjectionType.PREPEND)
            )
        }
    }

    fun surfaceRuleKeys(): List<ResourceKey<MaterialRule>> = biomeSurfaceRules().map { materialRuleKey(it.biome) }

    private fun materialRuleKey(biome: ResourceKey<Biome>): ResourceKey<MaterialRule> {
        return ResourceKey.create(
            Registries.MATERIAL_RULE,
            CommonClass.locate("surface_rule/${biome.identifier().path}")
        )
    }
}
