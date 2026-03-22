package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.world.gen.biome.HybridAquaticBiomes
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
            .addOptional(HybridAquaticBiomes.SEAGRASS_BED)
            .addOptional(HybridAquaticBiomes.RED_MEADOW)
            .addOptional(HybridAquaticBiomes.CORAL_REEF)
            .addOptional(HybridAquaticBiomes.TRENCH)
            .addOptional(HybridAquaticBiomes.LUKEWARM_TRENCH)
            .addOptional(HybridAquaticBiomes.WARM_TRENCH)
            .addOptional(HybridAquaticBiomes.COLD_TRENCH)
            .addOptional(HybridAquaticBiomes.FROZEN_TRENCH)
            .addOptional(HybridAquaticBiomes.TROPICAL_DEEP_CORAL_REEF)
            .addOptional(HybridAquaticBiomes.DEEP_CORAL_REEF)

        getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
            .forceAddTag(BiomeTags.IS_DEEP_OCEAN)
            .addOptional(HybridAquaticBiomes.SULFURIC_CAVES)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(BiomeTags.HAS_SHIPWRECK)
            .addOptional(HybridAquaticBiomes.SEAGRASS_BED)
            .addOptional(HybridAquaticBiomes.RED_MEADOW)
            .addOptional(HybridAquaticBiomes.CORAL_REEF)

        getOrCreateTagBuilder(BiomeTags.IS_OCEAN)
            .addOptional(HybridAquaticBiomes.SEAGRASS_BED)
            .addOptional(HybridAquaticBiomes.RED_MEADOW)
            .addOptional(HybridAquaticBiomes.CORAL_REEF)

        getOrCreateTagBuilder(BiomeTags.IS_RIVER)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(BiomeTags.IS_DEEP_OCEAN)
            .addOptional(HybridAquaticBiomes.TRENCH)
            .addOptional(HybridAquaticBiomes.LUKEWARM_TRENCH)
            .addOptional(HybridAquaticBiomes.WARM_TRENCH)
            .addOptional(HybridAquaticBiomes.COLD_TRENCH)
            .addOptional(HybridAquaticBiomes.FROZEN_TRENCH)
            .addOptional(HybridAquaticBiomes.TROPICAL_DEEP_CORAL_REEF)
            .addOptional(HybridAquaticBiomes.DEEP_CORAL_REEF)
            .addOptional(HybridAquaticBiomes.DEEP_WARM_OCEAN)
        //#endregion

        //#region Arctic Ocean Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.ARCTIC_OCEANS)
            .add(
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_ARCTIC_OCEANS)
            .add(Biomes.FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_ARCTIC_OCEANS)
            .add(Biomes.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ARCTIC_TRENCH)
            .addOptional(HybridAquaticBiomes.FROZEN_TRENCH)
        //#endregion

        //#region Cold Ocean Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.COLD_OCEANS)
            .add(
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS)
            .add(Biomes.COLD_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_COLD_OCEANS)
            .add(Biomes.DEEP_COLD_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COLD_TRENCH)
            .addOptional(HybridAquaticBiomes.COLD_TRENCH)
        //#endregion

        //#region Temperate Ocean Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.TEMPERATE_OCEANS)
            .add(
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS)
            .add(Biomes.OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS)
            .add(Biomes.DEEP_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TEMPERATE_TRENCH)
            .addOptional(HybridAquaticBiomes.TRENCH)
        //#endregion

        //#region Lukewarm Ocean Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS)
            .add(Biomes.LUKEWARM_OCEAN)
            .addOptional(ResourceLocation("still_life", "subtropical_shallow_ocean"))
            .addOptional(ResourceLocation("still_life", "tropical_shallow_ocean"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.LUKEWARM_OCEANS)
            .add(
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS)
            .add(Biomes.DEEP_LUKEWARM_OCEAN)
            .addOptional(HybridAquaticBiomes.TROPICAL_DEEP_CORAL_REEF)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.LUKEWARM_TRENCH)
            .addOptional(HybridAquaticBiomes.LUKEWARM_TRENCH)
            .addOptional(HybridAquaticBiomes.WARM_TRENCH)
        //#endregion

        //#region Warm Ocean Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.WARM_OCEAN)
            .add(Biomes.WARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_WARM_OCEAN)
            .addOptional(HybridAquaticBiomes.DEEP_WARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.WARM_TRENCH)
            .addOptional(HybridAquaticBiomes.WARM_TRENCH)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CORAL_REEF)
            .addOptional(HybridAquaticBiomes.CORAL_REEF)
            .addOptional(ResourceLocation("regions_unexplored", "rocky_reef"))
            .addOptional(ResourceLocation("biomeswevegone", "lush_stacks"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SEAGRASS_BED)
            .addOptional(HybridAquaticBiomes.SEAGRASS_BED)
            .addOptional(ResourceLocation("spawn", "seagrass_meadow"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.RED_MEADOW)
            .addOptional(HybridAquaticBiomes.RED_MEADOW)
        //#endregion

        //#region Misc Deep Sea Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.ALL_TRENCHES)
            .addOptional(HybridAquaticBiomes.FROZEN_TRENCH)
            .addOptional(HybridAquaticBiomes.COLD_TRENCH)
            .addOptional(HybridAquaticBiomes.TRENCH)
            .addOptional(HybridAquaticBiomes.LUKEWARM_TRENCH)
            .addOptional(HybridAquaticBiomes.WARM_TRENCH)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_REEF)
            .addOptional(HybridAquaticBiomes.DEEP_CORAL_REEF)
            .addOptional(HybridAquaticBiomes.TROPICAL_DEEP_CORAL_REEF)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.HAS_THERMAL_VENTS)
            .addOptional(HybridAquaticBiomes.SULFURIC_CAVES)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SULFURIC_CAVE)
            .addOptional(HybridAquaticBiomes.SULFURIC_CAVES)
        //#endregion

        //#region Beach Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.SANDY_BEACHES)
            .add(Biomes.BEACH)
            .addOptional(ResourceLocation("wythers", "tropical_beach"))
            .addOptional(ResourceLocation("biomesoplenty", "dune_beach"))
            .addOptional(ResourceLocation("biomeswevegone", "rainbow_beach"))
            .addOptional(ResourceLocation("terrestria", "volcanic_island_beach"))
            .addOptional(ResourceLocation("mysticsbiomes", "lagoon"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ROCKY_BEACHES)
            .add(Biomes.STONY_SHORE)
            .addOptional(HybridAquaticBiomes.BASALT_BEACH)
            .addOptional(ResourceLocation("biomesoplenty", "gravel_beach"))
            .addOptional(ResourceLocation("biomeswevegone", "dacite_shore"))
            .addOptional(ResourceLocation("biomeswevegone", "basalt_barrera"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TIDE_POOLS)
            .addOptional(HybridAquaticBiomes.TIDE_POOLS)
        //#endregion

        //#region River Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.RIVERS)
            .add(Biomes.RIVER)
            .addOptional(ResourceLocation("wythers", "jungle_river"))
            .addOptional(ResourceLocation("wythers", "tropical_forest_river"))
            .addOptional(ResourceLocation("terralith", "warm_river"))
            .addOptional(ResourceLocation("regions_unexplored", "tropical_river"))
            .addOptional(ResourceLocation("regions_unexplored", "muddy_river"))
            .addOptional(ResourceLocation("regions_unexplored", "cold_river"))
            .addOptional(ResourceLocation("riverredux", "sandy_river"))
            .addOptional(ResourceLocation("riverredux", "gravelly_river"))
            .addOptional(ResourceLocation("riverredux", "tropical_river"))
            .addOptional(ResourceLocation("riverredux", "carved_river"))
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_RIVERS)
            .addOptional(ResourceLocation("wythers", "jungle_river"))
            .addOptional(ResourceLocation("wythers", "tropical_forest_river"))
            .addOptional(ResourceLocation("terralith", "warm_river"))
            .addOptional(ResourceLocation("regions_unexplored", "tropical_river"))
            .addOptional(ResourceLocation("riverredux", "tropical_river"))
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COLD_RIVERS)
            .addOptional(ResourceLocation("riverredux", "gravelly_river"))
            .addOptional(ResourceLocation("regions_unexplored", "cold_river"))
        //#endregion

        //#region Misc Biome Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.JUNGLE)
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

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CHERRY)
            .add(Biomes.CHERRY_GROVE)
            .addOptional(ResourceLocation("regions_unexplored", "mauve_hills"))
            .addOptional(ResourceLocation("regions_unexplored", "magnolia_woodland"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CAVES)
            .add(Biomes.LUSH_CAVES)
            .add(Biomes.DRIPSTONE_CAVES)
            .add(Biomes.DEEP_DARK)
            .addOptional(ResourceLocation("regions_unexplored", "redstone_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "bioshroom_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "scorching_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "ancient_delta"))
            .addOptional(ResourceLocation("regions_unexplored", "prismachasm"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SWAMP)
            .add(Biomes.SWAMP)
            .addOptional(ResourceLocation("wythers", "waterlily_swamp"))
            .addOptional(ResourceLocation("terralith", "orchid_swamp"))
            .addOptional(ResourceLocation("biomesoplenty", "bayou"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_swamplands"))
            .addOptional(ResourceLocation("biomeswevegone", "bayou"))
            .addOptional(ResourceLocation("terrestria", "cypress_swamp"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MARSHES)
            .addOptional(ResourceLocation("regions_unexplored", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "wetland"))
            .addOptional(ResourceLocation("biomesoplenty", "floodplain"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MANGROVES)
            .add(Biomes.MANGROVE_SWAMP)
            .addOptional(ResourceLocation("biomeswevegone", "white_mangrove_marshes"))
        //#endregion

        //#region Misc Tags
        getOrCreateTagBuilder(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_BEACH)
        //#endregion

        //#region Compatibility Tags
            // rainbow reef
        getOrCreateTagBuilder(HybridAquaticBiomeTags.WARM_OCEANS)
            .addOptional(HybridAquaticBiomes.CORAL_REEF)

            // fintastic
        getOrCreateTagBuilder(HybridAquaticBiomeTags.MOONY_BIOMES)
            .addOptional(HybridAquaticBiomes.CORAL_REEF)
            .addOptional(HybridAquaticBiomes.BASALT_BEACH)
            .addOptional(HybridAquaticBiomes.TIDE_POOLS)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.PLECO_BIOMES)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ARAPAIMA_BIOMES)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CATFISH_BIOMES)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GUPPY_BIOMES)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MINNOW_SURFACE_BIOMES)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FWSHARK_BIOMES)
            .addOptional(HybridAquaticBiomes.TROPICAL_RIVER)
        //#endregion
    }
}