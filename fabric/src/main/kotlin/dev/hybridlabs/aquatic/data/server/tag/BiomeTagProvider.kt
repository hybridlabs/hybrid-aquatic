package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
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
        // spawn biomes

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SANDY_BEACHES)
            .add(Biomes.BEACH)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "wythers", "tropical_beach"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "dune_beach"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "rainbow_beach"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terrestria", "volcanic_island_beach"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "mysticsbiomes", "lagoon"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ROCKY_BEACHES)
            .add(Biomes.STONY_SHORE)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "gravel_beach"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "dacite_shore"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "basalt_barrera"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_BEACH)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ARCTIC_OCEANS)
            .add(
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_ARCTIC_OCEANS)
            .add(Biomes.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_ARCTIC_OCEANS)
            .add(Biomes.FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COLD_OCEANS)
            .add(
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_COLD_OCEANS)
            .add(Biomes.DEEP_COLD_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS)
            .add(Biomes.COLD_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TEMPERATE_OCEANS)
            .add(
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS)
            .add(Biomes.DEEP_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS)
            .add(Biomes.OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_OCEANS)
            .add(
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS)
            .add(Biomes.DEEP_LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS)
            .add(Biomes.LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.REEF)
            .add(Biomes.WARM_OCEAN)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "rocky_reef"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "lush_stacks"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.JUNGLE)
            .forceAddTag(BiomeTags.IS_JUNGLE)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "wythers", "flooded_jungle"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "rainforest"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "rocky_rainforest"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "crag_gardens"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "jacaranda_jungle"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "fragment_jungle"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "tropical_rainforest"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terrestria", "hemlock_rainforest"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terrestria", "hemlock_clearing"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terrestria", "rainbow_rainforest"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terrestria", "rainbow_rainforest_lake"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SWAMP)
            .add(Biomes.SWAMP)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "wythers", "waterlily_swamp"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terralith", "orchid_swamp"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "bayou"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "cypress_swamplands"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "bayou"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terrestria", "cypress_swamp"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MANGROVES)
            .add(Biomes.MANGROVE_SWAMP)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomeswevegone", "white_mangrove_marshes"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MARSHES)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "marsh"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "marsh"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "wetland"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "biomesoplenty", "floodplain"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.RIVERS)
            .add(Biomes.RIVER)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "wythers", "jungle_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "wythers", "tropical_forest_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terralith", "warm_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "tropical_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "muddy_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "cold_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "riverredux", "sandy_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "riverredux", "gravelly_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "riverredux", "tropical_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "riverredux", "carved_river"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_RIVERS)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "wythers", "jungle_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "wythers", "tropical_forest_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "terralith", "warm_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "tropical_river"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "riverredux", "tropical_river"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CHERRY)
            .add(Biomes.CHERRY_GROVE)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "mauve_hills"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "magnolia_woodland"
                )
            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CAVES)
            .add(Biomes.LUSH_CAVES)
            .add(Biomes.DRIPSTONE_CAVES)
            .add(Biomes.DEEP_DARK)
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "redstone_caves"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "bioshroom_caves"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "scorching_caves"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "ancient_delta"
                )
            )
            .addOptional(
                ResourceLocation.fromNamespaceAndPath(
                    "regions_unexplored", "prismachasm"
                )
            )
    }
}
