package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome

object HybridAquaticBiomeTags {

    val DUNGENESS_CRAB_SPAWN_BIOMES = create("dungeness_crab_spawn_biomes")
    val GHOST_CRAB_SPAWN_BIOMES = create("ghost_crab_spawn_biomes")
    val LIGHTFOOT_CRAB_SPAWN_BIOMES = create("lightfoot_crab_spawn_biomes")
    val FLOWER_CRAB_SPAWN_BIOMES = create("flower_crab_spawn_biomes")

    val BOTTLE_SPAWN_BIOMES = create("bottle_spawn_biomes")

    val OCEAN = create("ocean")
    val DEEP_OCEAN = create("deep_ocean")
    val ALL_OCEANS = create("all_oceans")
    val ALL_DEEP_OCEANS = create("deep_oceans")
    val COLD_OCEANS = create("cold_oceans")
    val DEEP_COLD_OCEANS = create("deep_cold_oceans")
    val ALL_COLD_OCEANS = create("all_cold_oceans")
    val WARM_OCEANS = create("warm_oceans")
    val DEEP_WARM_OCEANS = create("deep_warm_oceans")
    val ALL_WARM_OCEANS = create("all_warm_oceans")
    val REEF = create("reef")
    val TROPICAL_FRESHWATER = create("tropical_freshwater")
    val CHERRY = create("cherry")
    val SWAMPLAND = create("swampland")
    val RIVERS = create("rivers")
    val TROPICAL_BEACHES = create("tropical_beaches")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(HybridAquatic.MOD_ID, id))
    }
}
