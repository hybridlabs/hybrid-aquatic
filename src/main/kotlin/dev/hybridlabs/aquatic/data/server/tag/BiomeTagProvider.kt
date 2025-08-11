package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider<Biome>(output, RegistryKeys.BIOME, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup?) {
        // spawn biomes

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SANDY_BEACHES)
            .add(BiomeKeys.BEACH)
            .addOptional(Identifier.of("wythers", "tropical_beach"))
            .addOptional(Identifier.of("biomesoplenty", "dune_beach"))
            .addOptional(Identifier.of("biomeswevegone", "rainbow_beach"))
            .addOptional(Identifier.of("terrestria", "volcanic_island_beach"))
            .addOptional(Identifier.of("mysticsbiomes", "lagoon"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ROCKY_BEACHES)
            .add(BiomeKeys.STONY_SHORE)
            .addOptional(Identifier.of("biomesoplenty", "gravel_beach"))
            .addOptional(Identifier.of("biomeswevegone", "dacite_shore"))
            .addOptional(Identifier.of("biomeswevegone", "basalt_barrera"))

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
            .addOptional(Identifier.of("alexscaves", "abyssal_chasm"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS)
            .add(BiomeKeys.DEEP_OCEAN)
            .addOptional(Identifier.of("alexscaves", "abyssal_chasm"))

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
            .addOptional(Identifier.of("regions_unexplored", "rocky_reef"))
            .addOptional(Identifier.of("biomeswevegone", "lush_stacks"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.JUNGLE)
            .forceAddTag(BiomeTags.IS_JUNGLE)
            .addOptional(Identifier.of("wythers", "flooded_jungle"))
            .addOptional(Identifier.of("biomesoplenty", "rainforest"))
            .addOptional(Identifier.of("biomesoplenty", "rocky_rainforest"))
            .addOptional(Identifier.of("biomeswevegone", "crag_gardens"))
            .addOptional(Identifier.of("biomeswevegone", "jacaranda_jungle"))
            .addOptional(Identifier.of("biomeswevegone", "fragment_jungle"))
            .addOptional(Identifier.of("biomeswevegone", "tropical_rainforest"))
            .addOptional(Identifier.of("terrestria", "hemlock_rainforest"))
            .addOptional(Identifier.of("terrestria", "hemlock_clearing"))
            .addOptional(Identifier.of("terrestria", "rainbow_rainforest"))
            .addOptional(Identifier.of("terrestria", "rainbow_rainforest_lake"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SWAMP)
            .add(BiomeKeys.SWAMP)
            .addOptional(Identifier.of("wythers", "waterlily_swamp"))
            .addOptional(Identifier.of("terralith", "orchid_swamp"))
            .addOptional(Identifier.of("biomesoplenty", "bayou"))
            .addOptional(Identifier.of("biomeswevegone", "cypress_swamplands"))
            .addOptional(Identifier.of("biomeswevegone", "cypress_wetlands"))
            .addOptional(Identifier.of("biomeswevegone", "bayou"))
            .addOptional(Identifier.of("terrestria", "cypress_swamp"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MANGROVES)
            .add(BiomeKeys.MANGROVE_SWAMP)
            .addOptional(Identifier.of("biomeswevegone", "white_mangrove_marshes"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MARSHES)
            .addOptional(Identifier.of("regions_unexplored", "marsh"))
            .addOptional(Identifier.of("biomesoplenty", "marsh"))
            .addOptional(Identifier.of("biomesoplenty", "wetland"))
            .addOptional(Identifier.of("biomesoplenty", "floodplain"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.RIVERS)
            .add(BiomeKeys.RIVER)
            .addOptional(Identifier.of("wythers", "jungle_river"))
            .addOptional(Identifier.of("wythers", "tropical_forest_river"))
            .addOptional(Identifier.of("terralith", "warm_river"))
            .addOptional(Identifier.of("regions_unexplored", "tropical_river"))
            .addOptional(Identifier.of("regions_unexplored", "muddy_river"))
            .addOptional(Identifier.of("regions_unexplored", "cold_river"))
            .addOptional(Identifier.of("riverredux", "sandy_river"))
            .addOptional(Identifier.of("riverredux", "gravelly_river"))
            .addOptional(Identifier.of("riverredux", "tropical_river"))
            .addOptional(Identifier.of("riverredux", "carved_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_RIVERS)
            .addOptional(Identifier.of("wythers", "jungle_river"))
            .addOptional(Identifier.of("wythers", "tropical_forest_river"))
            .addOptional(Identifier.of("terralith", "warm_river"))
            .addOptional(Identifier.of("regions_unexplored", "tropical_river"))
            .addOptional(Identifier.of("riverredux", "tropical_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CHERRY)
            .add(BiomeKeys.CHERRY_GROVE)
            .addOptional(Identifier.of("regions_unexplored", "mauve_hills"))
            .addOptional(Identifier.of("regions_unexplored", "magnolia_woodland"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CAVES)
            .add(BiomeKeys.LUSH_CAVES)
            .add(BiomeKeys.DRIPSTONE_CAVES)
            .add(BiomeKeys.DEEP_DARK)
            .addOptional(Identifier.of("regions_unexplored", "redstone_caves"))
            .addOptional(Identifier.of("regions_unexplored", "bioshroom_caves"))
            .addOptional(Identifier.of("regions_unexplored", "scorching_caves"))
            .addOptional(Identifier.of("regions_unexplored", "ancient_delta"))
            .addOptional(Identifier.of("regions_unexplored", "prismachasm"))
            .addOptional(Identifier.of("alexscaves", "abyssal_chasm"))
    }
}
