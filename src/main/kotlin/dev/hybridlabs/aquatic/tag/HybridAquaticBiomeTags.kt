package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome

object HybridAquaticBiomeTags {

    val CLOWNFISH_SPAWN_BIOMES = create("clownfish_spawn_biomes")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.of(RegistryKeys.BIOME, Identifier(HybridAquatic.MOD_ID, id))
    }
}