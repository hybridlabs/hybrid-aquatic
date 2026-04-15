package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.tag.HABiomeTags
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(output, Registries.BIOME, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {

        //#region Vanilla Tags
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_OCEAN)
            .addOptional(HABiomes.SEAGRASS_BED)
            .addOptional(HABiomes.RED_MEADOW)
            .addOptional(HABiomes.CORAL_REEF)
            .addOptional(HABiomes.TRENCH)
            .addOptional(HABiomes.LUKEWARM_TRENCH)
            .addOptional(HABiomes.WARM_TRENCH)
            .addOptional(HABiomes.COLD_TRENCH)
            .addOptional(HABiomes.FROZEN_TRENCH)
            .addOptional(HABiomes.TROPICAL_DEEP_CORAL_REEF)
            .addOptional(HABiomes.DEEP_CORAL_REEF)

        getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
            .forceAddTag(BiomeTags.IS_DEEP_OCEAN)
            .addOptional(HABiomes.SULFURIC_CAVES)
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(BiomeTags.HAS_SHIPWRECK)
            .addOptional(HABiomes.SEAGRASS_BED)
            .addOptional(HABiomes.RED_MEADOW)
            .addOptional(HABiomes.CORAL_REEF)

        getOrCreateTagBuilder(BiomeTags.IS_OCEAN)
            .addOptional(HABiomes.SEAGRASS_BED)
            .addOptional(HABiomes.RED_MEADOW)
            .addOptional(HABiomes.CORAL_REEF)

        getOrCreateTagBuilder(BiomeTags.IS_RIVER)
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(BiomeTags.IS_DEEP_OCEAN)
            .addOptional(HABiomes.TROPICAL_DEEP_CORAL_REEF)
            .addOptional(HABiomes.DEEP_CORAL_REEF)
            .addOptional(HABiomes.DEEP_WARM_OCEAN)
        //#endregion

        //#region Arctic Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.FROZEN_OCEANS)
            .add(
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN
            )

        getOrCreateTagBuilder(HABiomeTags.SHALLOW_FROZEN_OCEANS)
            .add(Biomes.FROZEN_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.DEEP_FROZEN_OCEANS)
            .add(Biomes.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.FROZEN_TRENCH)
            .addOptional(HABiomes.FROZEN_TRENCH)
        //#endregion

        //#region Cold Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.COLD_OCEANS)
            .add(
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN
            )

        getOrCreateTagBuilder(HABiomeTags.SHALLOW_COLD_OCEANS)
            .add(Biomes.COLD_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.DEEP_COLD_OCEANS)
            .add(Biomes.DEEP_COLD_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.COLD_TRENCH)
            .addOptional(HABiomes.COLD_TRENCH)
        //#endregion

        //#region Temperate Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.TEMPERATE_OCEANS)
            .add(
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN
            )

        getOrCreateTagBuilder(HABiomeTags.SHALLOW_TEMPERATE_OCEANS)
            .add(Biomes.OCEAN)

        getOrCreateTagBuilder(HABiomeTags.DEEP_TEMPERATE_OCEANS)
            .add(Biomes.DEEP_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.TEMPERATE_TRENCH)
            .addOptional(HABiomes.TRENCH)
        //#endregion

        //#region Lukewarm Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.SHALLOW_LUKEWARM_OCEANS)
            .add(Biomes.LUKEWARM_OCEAN)
            .addOptional(ResourceLocation("still_life", "subtropical_shallow_ocean"))
            .addOptional(ResourceLocation("still_life", "tropical_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.LUKEWARM_OCEANS)
            .add(
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN
            )

        getOrCreateTagBuilder(HABiomeTags.DEEP_LUKEWARM_OCEANS)
            .add(Biomes.DEEP_LUKEWARM_OCEAN)
            .addOptional(HABiomes.TROPICAL_DEEP_CORAL_REEF)

        getOrCreateTagBuilder(HABiomeTags.LUKEWARM_TRENCH)
            .addOptional(HABiomes.LUKEWARM_TRENCH)
            .addOptional(HABiomes.WARM_TRENCH)
        //#endregion

        //#region Warm Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.SHALLOW_WARM_OCEANS)
            .add(Biomes.WARM_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.WARM_OCEANS)
            .add(Biomes.WARM_OCEAN)
            .addOptional(HABiomes.DEEP_WARM_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.DEEP_WARM_OCEANS)
            .addOptional(HABiomes.DEEP_WARM_OCEAN)
            .addOptional(ResourceLocation("spawn", "deep_warm_ocean"))

        getOrCreateTagBuilder(HABiomeTags.WARM_TRENCH)
            .addOptional(HABiomes.WARM_TRENCH)

        getOrCreateTagBuilder(HABiomeTags.CORAL_REEF)
            .addOptional(HABiomes.CORAL_REEF)
            .addOptional(ResourceLocation("regions_unexplored", "rocky_reef"))
            .addOptional(ResourceLocation("biomeswevegone", "lush_stacks"))

        getOrCreateTagBuilder(HABiomeTags.SEAGRASS_BED)
            .addOptional(HABiomes.SEAGRASS_BED)
            .addOptional(ResourceLocation("spawn", "seagrass_meadow"))

        getOrCreateTagBuilder(HABiomeTags.RED_MEADOW)
            .addOptional(HABiomes.RED_MEADOW)
        //#endregion

        //#region Misc Deep Sea Tags
        getOrCreateTagBuilder(HABiomeTags.ALL_TRENCHES)
            .addOptional(HABiomes.FROZEN_TRENCH)
            .addOptional(HABiomes.COLD_TRENCH)
            .addOptional(HABiomes.TRENCH)
            .addOptional(HABiomes.LUKEWARM_TRENCH)
            .addOptional(HABiomes.WARM_TRENCH)

        getOrCreateTagBuilder(HABiomeTags.DEEP_REEF)
            .addOptional(HABiomes.DEEP_CORAL_REEF)
            .addOptional(HABiomes.TROPICAL_DEEP_CORAL_REEF)
            .addOptional(ResourceLocation("spawn", "deep_warm_ocean"))

        getOrCreateTagBuilder(HABiomeTags.HAS_THERMAL_VENTS)
            .addOptional(HABiomes.SULFURIC_CAVES)

        getOrCreateTagBuilder(HABiomeTags.SULFURIC_CAVE)
            .addOptional(HABiomes.SULFURIC_CAVES)
        //#endregion

        //#region Beach Tags
        getOrCreateTagBuilder(HABiomeTags.SANDY_BEACHES)
            .add(Biomes.BEACH)
            .addOptional(ResourceLocation("wythers", "tropical_beach"))
            .addOptional(ResourceLocation("biomesoplenty", "dune_beach"))
            .addOptional(ResourceLocation("biomeswevegone", "rainbow_beach"))
            .addOptional(ResourceLocation("terrestria", "volcanic_island_beach"))
            .addOptional(ResourceLocation("mysticsbiomes", "lagoon"))

        getOrCreateTagBuilder(HABiomeTags.ROCKY_BEACHES)
            .add(Biomes.STONY_SHORE)
            .addOptional(HABiomes.BASALT_BEACH)
            .addOptional(ResourceLocation("biomesoplenty", "gravel_beach"))
            .addOptional(ResourceLocation("biomeswevegone", "dacite_shore"))
            .addOptional(ResourceLocation("biomeswevegone", "basalt_barrera"))

        getOrCreateTagBuilder(HABiomeTags.TIDE_POOLS)
            .addOptional(HABiomes.TIDE_POOLS)
        //#endregion

        //#region River Tags
        getOrCreateTagBuilder(HABiomeTags.RIVERS)
            .add(Biomes.RIVER)
            .addOptional(ResourceLocation("regions_unexplored", "muddy_river"))
            .addOptional(ResourceLocation("riverredux", "sandy_river"))
            .addOptional(ResourceLocation("riverredux", "carved_river"))

        getOrCreateTagBuilder(HABiomeTags.TROPICAL_RIVERS)
            .addOptional(ResourceLocation("wythers", "jungle_river"))
            .addOptional(ResourceLocation("wythers", "tropical_forest_river"))
            .addOptional(ResourceLocation("terralith", "warm_river"))
            .addOptional(ResourceLocation("regions_unexplored", "tropical_river"))
            .addOptional(ResourceLocation("riverredux", "tropical_river"))
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HABiomeTags.COLD_RIVERS)
            .add(Biomes.FROZEN_RIVER)
            .addOptional(ResourceLocation("riverredux", "gravelly_river"))
            .addOptional(ResourceLocation("regions_unexplored", "cold_river"))
        //#endregion

        //#region Misc Biome Tags
        getOrCreateTagBuilder(HABiomeTags.JUNGLE)
            .forceAddTag(BiomeTags.IS_JUNGLE)
            .addOptional(ResourceLocation("wythers", "flooded_jungle"))
            .addOptional(ResourceLocation("biomesoplenty", "rainforest"))
            .addOptional(ResourceLocation("biomesoplenty", "rocky_rainforest"))
            .addOptional(ResourceLocation("biomeswevegone", "crag_gardens"))
            .addOptional(ResourceLocation("biomeswevegone", "jacaranda_jungle"))
            .addOptional(ResourceLocation("biomeswevegone", "fragment_jungle"))
            .addOptional(ResourceLocation("biomeswevegone", "tropical_rainforest"))
            .addOptional(ResourceLocation("terrestria", "hemlock_rainforest"))
            .addOptional(ResourceLocation("terrestria", "hemlock_clearing"))
            .addOptional(ResourceLocation("terrestria", "rainbow_rainforest"))
            .addOptional(ResourceLocation("terrestria", "rainbow_rainforest_lake"))

        getOrCreateTagBuilder(HABiomeTags.CHERRY)
            .add(Biomes.CHERRY_GROVE)
            .addOptional(ResourceLocation("regions_unexplored", "mauve_hills"))
            .addOptional(ResourceLocation("regions_unexplored", "magnolia_woodland"))

        getOrCreateTagBuilder(HABiomeTags.CAVES)
            .add(Biomes.LUSH_CAVES)
            .add(Biomes.DRIPSTONE_CAVES)
            .add(Biomes.DEEP_DARK)
            .addOptional(ResourceLocation("regions_unexplored", "redstone_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "bioshroom_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "scorching_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "ancient_delta"))
            .addOptional(ResourceLocation("regions_unexplored", "prismachasm"))

        getOrCreateTagBuilder(HABiomeTags.SWAMP)
            .add(Biomes.SWAMP)
            .addOptional(ResourceLocation("wythers", "waterlily_swamp"))
            .addOptional(ResourceLocation("terralith", "orchid_swamp"))
            .addOptional(ResourceLocation("biomesoplenty", "bayou"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_swamplands"))
            .addOptional(ResourceLocation("biomeswevegone", "bayou"))
            .addOptional(ResourceLocation("terrestria", "cypress_swamp"))

        getOrCreateTagBuilder(HABiomeTags.MARSHES)
            .addOptional(ResourceLocation("regions_unexplored", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "wetland"))
            .addOptional(ResourceLocation("biomesoplenty", "floodplain"))

        getOrCreateTagBuilder(HABiomeTags.MANGROVES)
            .add(Biomes.MANGROVE_SWAMP)
            .addOptional(ResourceLocation("biomeswevegone", "white_mangrove_marshes"))
        //#endregion

        //#region Misc Tags
        getOrCreateTagBuilder(HABiomeTags.BOTTLE_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_BEACH)
        //#endregion

        //#region Compatibility Tags
            // rainbow reef
        getOrCreateTagBuilder(HABiomeTags.RR_WARM_OCEANS)
            .addOptional(HABiomes.CORAL_REEF)

            // fintastic
        getOrCreateTagBuilder(HABiomeTags.MOONY_BIOMES)
            .addOptional(HABiomes.CORAL_REEF)
            .addOptional(HABiomes.BASALT_BEACH)
            .addOptional(HABiomes.TIDE_POOLS)

        getOrCreateTagBuilder(HABiomeTags.PLECO_BIOMES)
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HABiomeTags.ARAPAIMA_BIOMES)
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HABiomeTags.CATFISH_BIOMES)
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HABiomeTags.GUPPY_BIOMES)
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HABiomeTags.MINNOW_SURFACE_BIOMES)
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HABiomeTags.FWSHARK_BIOMES)
            .addOptional(HABiomes.TROPICAL_RIVER)
        //#endregion
    }
}