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

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DUNGENESS_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
        )
            .addOptional(Identifier.of("regions_unexplored", "rocky_reef"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GHOST_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
        )
            .addOptional(Identifier.of("wythers", "tropical_beach"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.LIGHTFOOT_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
        )
            .addOptional(Identifier.of("wythers", "tropical_beach"))
            .addOptional(Identifier.of("regions_unexplored", "rocky_reef"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FLOWER_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH
        )
            .addOptional(Identifier.of("wythers", "tropical_beach"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_BEACHES).add(
            BiomeKeys.BEACH,
        )
            .addOptional(Identifier.of("wythers", "tropical_beach"))
            .addOptional(Identifier.of("regions_unexplored", "rocky_reef"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_DEEP_OCEAN)
            .forceAddTag(BiomeTags.IS_BEACH)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.OCEAN)
            .add(BiomeKeys.OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_OCEAN)
            .add(BiomeKeys.DEEP_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ALL_DEEP_OCEANS)
            .forceAddTag(BiomeTags.IS_DEEP_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ALL_OCEANS)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_DEEP_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COLD_OCEANS)
            .add(BiomeKeys.COLD_OCEAN,
                BiomeKeys.FROZEN_OCEAN)

            getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_COLD_OCEANS)
            .add(BiomeKeys.DEEP_COLD_OCEAN,
                BiomeKeys.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ALL_COLD_OCEANS)
            .add(BiomeKeys.COLD_OCEAN,
                BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.DEEP_COLD_OCEAN,
                BiomeKeys.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.WARM_OCEANS)
            .add(BiomeKeys.WARM_OCEAN,
                BiomeKeys.LUKEWARM_OCEAN)
            .addOptional(Identifier.of("regions_unexplored", "rocky_reef"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_WARM_OCEANS)
            .add(BiomeKeys.DEEP_LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ALL_WARM_OCEANS)
            .add(BiomeKeys.WARM_OCEAN,
                BiomeKeys.LUKEWARM_OCEAN,
                BiomeKeys.DEEP_LUKEWARM_OCEAN)
            .addOptional(Identifier.of("regions_unexplored", "rocky_reef"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.REEF)
            .add(BiomeKeys.WARM_OCEAN)
            .addOptional(Identifier.of("regions_unexplored", "rocky_reef"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_FRESHWATER)
            .forceAddTag(BiomeTags.IS_JUNGLE)
            .add(BiomeKeys.JUNGLE,
                BiomeKeys.BAMBOO_JUNGLE,
                BiomeKeys.SPARSE_JUNGLE)
            .addOptional(Identifier.of("wythers", "jungle_river"))
            .addOptional(Identifier.of("wythers", "tropical_forest_river"))
            .addOptional(Identifier.of("wythers", "flooded_jungle"))
            .addOptional(Identifier.of("terralith", "warm_river"))
            .addOptional(Identifier.of("regions_unexplored", "tropical_river"))
            .addOptional(Identifier.of("regions_unexplored", "muddy_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SWAMPLAND)
            .forceAddTag(BiomeTags.SWAMP_HUT_HAS_STRUCTURE)
            .add(BiomeKeys.SWAMP,
                BiomeKeys.MANGROVE_SWAMP)
            .addOptional(Identifier.of("wythers", "waterlily_swamp"))
            .addOptional(Identifier.of("terralith", "orchid_swamp"))
            .addOptional(Identifier.of("regions_unexplored", "marsh"))
            .addOptional(Identifier.of("regions_unexplored", "muddy_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.RIVERS)
            .forceAddTag(BiomeTags.IS_RIVER)
            .add(BiomeKeys.RIVER)
            .addOptional(Identifier.of("wythers", "jungle_river"))
            .addOptional(Identifier.of("wythers", "tropical_forest_river"))
            .addOptional(Identifier.of("terralith", "warm_river"))
            .addOptional(Identifier.of("regions_unexplored", "tropical_river"))
            .addOptional(Identifier.of("regions_unexplored", "muddy_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CHERRY)
            .add(
                BiomeKeys.CHERRY_GROVE,
            )
            .addOptional(Identifier.of("regions_unexplored", "mauve_hills"))
            .addOptional(Identifier.of("regions_unexplored", "magnolia_woodland"))
    }
}
