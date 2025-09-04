package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider<Biome>(output, RegistryKeys.BIOME, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup?) {
        // spawn biomes

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SANDY_BEACHES)
            .add(BiomeKeys.BEACH)
            .addOptional(ResourceLocation("wythers", "tropical_beach"))
            .addOptional(ResourceLocation("biomesoplenty", "dune_beach"))
            .addOptional(ResourceLocation("biomeswevegone", "rainbow_beach"))
            .addOptional(ResourceLocation("terrestria", "volcanic_island_beach"))
            .addOptional(ResourceLocation("mysticsbiomes", "lagoon"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ROCKY_BEACHES)
            .add(BiomeKeys.STONY_SHORE)
            .addOptional(ResourceLocation("biomesoplenty", "gravel_beach"))
            .addOptional(ResourceLocation("biomeswevegone", "dacite_shore"))
            .addOptional(ResourceLocation("biomeswevegone", "basalt_barrera"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_BEACH)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ARCTIC_OCEANS)
            .add(BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_ARCTIC_OCEANS)
            .add(BiomeKeys.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_ARCTIC_OCEANS)
            .add(BiomeKeys.FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COLD_OCEANS)
            .add(BiomeKeys.COLD_OCEAN,
                BiomeKeys.DEEP_COLD_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_COLD_OCEANS)
            .add(BiomeKeys.DEEP_COLD_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS)
            .add(BiomeKeys.COLD_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TEMPERATE_OCEANS)
            .add(BiomeKeys.OCEAN,
                BiomeKeys.DEEP_OCEAN)
            .addOptional(ResourceLocation("alexscaves", "abyssal_chasm"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS)
            .add(BiomeKeys.DEEP_OCEAN)
            .addOptional(ResourceLocation("alexscaves", "abyssal_chasm"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS)
            .add(BiomeKeys.OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_OCEANS)
            .add(BiomeKeys.LUKEWARM_OCEAN,
                BiomeKeys.DEEP_LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS)
            .add(BiomeKeys.DEEP_LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS)
            .add(BiomeKeys.LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.REEF)
            .add(BiomeKeys.WARM_OCEAN)
            .addOptional(ResourceLocation("regions_unexplored", "rocky_reef"))
            .addOptional(ResourceLocation("biomeswevegone", "lush_stacks"))

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

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SWAMP)
            .add(BiomeKeys.SWAMP)
            .addOptional(ResourceLocation("wythers", "waterlily_swamp"))
            .addOptional(ResourceLocation("terralith", "orchid_swamp"))
            .addOptional(ResourceLocation("biomesoplenty", "bayou"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_swamplands"))
            .addOptional(ResourceLocation("biomeswevegone", "cypress_wetlands"))
            .addOptional(ResourceLocation("biomeswevegone", "bayou"))
            .addOptional(ResourceLocation("terrestria", "cypress_swamp"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MANGROVES)
            .add(BiomeKeys.MANGROVE_SWAMP)
            .addOptional(ResourceLocation("biomeswevegone", "white_mangrove_marshes"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MARSHES)
            .addOptional(ResourceLocation("regions_unexplored", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "marsh"))
            .addOptional(ResourceLocation("biomesoplenty", "wetland"))
            .addOptional(ResourceLocation("biomesoplenty", "floodplain"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.RIVERS)
            .add(BiomeKeys.RIVER)
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

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_RIVERS)
            .addOptional(ResourceLocation("wythers", "jungle_river"))
            .addOptional(ResourceLocation("wythers", "tropical_forest_river"))
            .addOptional(ResourceLocation("terralith", "warm_river"))
            .addOptional(ResourceLocation("regions_unexplored", "tropical_river"))
            .addOptional(ResourceLocation("riverredux", "tropical_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CHERRY)
            .add(BiomeKeys.CHERRY_GROVE)
            .addOptional(ResourceLocation("regions_unexplored", "mauve_hills"))
            .addOptional(ResourceLocation("regions_unexplored", "magnolia_woodland"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CAVES)
            .add(BiomeKeys.LUSH_CAVES)
            .add(BiomeKeys.DRIPSTONE_CAVES)
            .add(BiomeKeys.DEEP_DARK)
            .addOptional(ResourceLocation("regions_unexplored", "redstone_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "bioshroom_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "scorching_caves"))
            .addOptional(ResourceLocation("regions_unexplored", "ancient_delta"))
            .addOptional(ResourceLocation("regions_unexplored", "prismachasm"))
            .addOptional(ResourceLocation("alexscaves", "abyssal_chasm"))
    }
}
