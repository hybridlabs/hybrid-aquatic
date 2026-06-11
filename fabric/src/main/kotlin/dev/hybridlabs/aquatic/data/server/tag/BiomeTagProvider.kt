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

        getOrCreateTagBuilder(BiomeTags.IS_OCEAN)
            .addOptional(HABiomes.SEAGRASS_BED)
            .addOptional(HABiomes.RED_MEADOW)
            .addOptional(HABiomes.CORAL_REEF)

        getOrCreateTagBuilder(BiomeTags.IS_DEEP_OCEAN)
            .addOptional(HABiomes.TROPICAL_DEEP_CORAL_REEF)
            .addOptional(HABiomes.DEEP_CORAL_REEF)
            .addOptional(HABiomes.DEEP_WARM_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.HA_DEEP_OCEANS)
            .addOptional(HABiomes.DEEP_WARM_OCEAN)
            .addOptional(Biomes.DEEP_OCEAN)
            .addOptional(Biomes.DEEP_COLD_OCEAN)
            .addOptional(Biomes.DEEP_FROZEN_OCEAN)
            .addOptional(Biomes.DEEP_LUKEWARM_OCEAN)

        getOrCreateTagBuilder(BiomeTags.IS_RIVER)
            .addOptional(HABiomes.TROPICAL_RIVER)
        //#endregion

        //#region Arctic Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.FROZEN_OCEANS)
            .add(
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "arctic_deep_ocean"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "arctic_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.SHALLOW_FROZEN_OCEANS)
            .add(Biomes.FROZEN_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "arctic_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.DEEP_FROZEN_OCEANS)
            .add(Biomes.DEEP_FROZEN_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "arctic_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.FROZEN_TRENCH)
            .addOptional(HABiomes.FROZEN_TRENCH)
        //#endregion

        //#region Cold Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.COLD_OCEANS)
            .add(
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "cold_shallow_ocean"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "cold_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.SHALLOW_COLD_OCEANS)
            .add(Biomes.COLD_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "cold_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.DEEP_COLD_OCEANS)
            .add(Biomes.DEEP_COLD_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "cold_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.COLD_TRENCH)
            .addOptional(HABiomes.COLD_TRENCH)
        //#endregion

        //#region Temperate Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.TEMPERATE_OCEANS)
            .add(
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_shallow_ocean"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.SHALLOW_TEMPERATE_OCEANS)
            .add(Biomes.OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.DEEP_TEMPERATE_OCEANS)
            .add(Biomes.DEEP_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.TEMPERATE_TRENCH)
            .addOptional(HABiomes.TRENCH)
        //#endregion

        //#region Lukewarm Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.SHALLOW_LUKEWARM_OCEANS)
            .add(Biomes.LUKEWARM_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "subtropical_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.LUKEWARM_OCEANS)
            .add(
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "subtropical_shallow_ocean"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "subtropical_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.DEEP_LUKEWARM_OCEANS)
            .add(Biomes.DEEP_LUKEWARM_OCEAN)
            .addOptional(HABiomes.TROPICAL_DEEP_CORAL_REEF)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "subtropical_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.LUKEWARM_TRENCH)
            .addOptional(HABiomes.LUKEWARM_TRENCH)
            .addOptional(HABiomes.WARM_TRENCH)
        //#endregion

        //#region Warm Ocean Tags
        getOrCreateTagBuilder(HABiomeTags.SHALLOW_WARM_OCEANS)
            .add(Biomes.WARM_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "tropical_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.WARM_OCEANS)
            .add(Biomes.WARM_OCEAN)
            .addOptional(HABiomes.DEEP_WARM_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "tropical_deep_ocean"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "tropical_shallow_ocean"))

        getOrCreateTagBuilder(HABiomeTags.DEEP_WARM_OCEANS)
            .addOptional(HABiomes.DEEP_WARM_OCEAN)
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "deep_warm_ocean"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "tropical_deep_ocean"))

        getOrCreateTagBuilder(HABiomeTags.WARM_TRENCH)
            .addOptional(HABiomes.WARM_TRENCH)

        getOrCreateTagBuilder(HABiomeTags.CORAL_REEF)
            .addOptional(HABiomes.CORAL_REEF)
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "rocky_reef"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "lush_stacks"))

        getOrCreateTagBuilder(HABiomeTags.SEAGRASS_BED)
            .addOptional(HABiomes.SEAGRASS_BED)
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "seagrass_meadow"))

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
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "deep_warm_ocean"))

        getOrCreateTagBuilder(HABiomeTags.HAS_WHALE_FALL)
            .addTag(HABiomeTags.ALL_TRENCHES)
            .addTag(BiomeTags.IS_DEEP_OCEAN)

        getOrCreateTagBuilder(HABiomeTags.HAS_THERMAL_VENTS)
            .addOptional(HABiomes.SULFURIC_CAVES)

        getOrCreateTagBuilder(HABiomeTags.SULFURIC_CAVE)
            .addOptional(HABiomes.SULFURIC_CAVES)
        //#endregion

        //#region Beach Tags
        getOrCreateTagBuilder(HABiomeTags.SANDY_BEACHES)
            .add(Biomes.BEACH)
            .addOptional(ResourceLocation.fromNamespaceAndPath("wythers", "tropical_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "dune_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "rainbow_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terrestria", "volcanic_island_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "lagoon"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "mediterranean_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "arid_beach"))

        getOrCreateTagBuilder(HABiomeTags.ROCKY_BEACHES)
            .add(Biomes.STONY_SHORE)
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "gravel_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "dacite_shore"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "basalt_barrera"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "taiga_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "tundra_beach"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "arctic_beach"))

        getOrCreateTagBuilder(HABiomeTags.TIDE_POOLS)
            .addOptional(HABiomes.TIDE_POOLS)
        //#endregion

        //#region River Tags
        getOrCreateTagBuilder(HABiomeTags.RIVERS)
            .add(Biomes.RIVER)
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "muddy_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("riverredux", "sandy_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("riverredux", "carved_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "warm_temperate_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "mediterranean_river"))

        getOrCreateTagBuilder(HABiomeTags.TROPICAL_RIVERS)
            .addOptional(ResourceLocation.fromNamespaceAndPath("wythers", "jungle_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wythers", "tropical_forest_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terralith", "warm_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "tropical_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("riverredux", "tropical_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "steppe_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "tropical_rainforest_river"))
            .addOptional(HABiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HABiomeTags.COLD_RIVERS)
            .add(Biomes.FROZEN_RIVER)
            .addOptional(ResourceLocation.fromNamespaceAndPath("riverredux", "gravelly_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "cold_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "arctic_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "tundra_river"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "boreal_river"))
        //#endregion

        //#region Misc Biome Tags
        getOrCreateTagBuilder(HABiomeTags.JUNGLE)
            .forceAddTag(BiomeTags.IS_JUNGLE)
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "eucalyptus_forest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "rainforest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "sparse_rainforest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wythers", "flooded_jungle"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "rainforest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "rocky_rainforest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "crag_gardens"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "jacaranda_jungle"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "fragment_jungle"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "tropical_rainforest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terrestria", "hemlock_rainforest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terrestria", "hemlock_clearing"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terrestria", "rainbow_rainforest"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terrestria", "rainbow_rainforest_lake"))

        getOrCreateTagBuilder(HABiomeTags.CHERRY)
            .add(Biomes.CHERRY_GROVE)
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "mauve_hills"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "magnolia_woodland"))

        getOrCreateTagBuilder(HABiomeTags.CAVES)
            .add(Biomes.LUSH_CAVES)
            .add(Biomes.DRIPSTONE_CAVES)
            .add(Biomes.DEEP_DARK)
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "redstone_caves"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "bioshroom_caves"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "scorching_caves"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "ancient_delta"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "prismachasm"))

        getOrCreateTagBuilder(HABiomeTags.SWAMP)
            .add(Biomes.SWAMP)
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "bayou"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wythers", "waterlily_swamp"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terralith", "orchid_swamp"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "bayou"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "cypress_swamplands"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "bayou"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("terrestria", "cypress_swamp"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_swamp"))

        getOrCreateTagBuilder(HABiomeTags.MARSHES)
            .addOptional(ResourceLocation.fromNamespaceAndPath("regions_unexplored", "marsh"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "marsh"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "wetland"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomesoplenty", "floodplain"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "bog"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "temperate_marsh"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "fen"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "mire"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "flooded_grasslands"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "mediterranean_marsh"))

        getOrCreateTagBuilder(HABiomeTags.MANGROVES)
            .add(Biomes.MANGROVE_SWAMP)
            .addOptional(ResourceLocation.fromNamespaceAndPath("biomeswevegone", "white_mangrove_marshes"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("still_life", "mangrove_marsh"))
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