package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticBlockTags {
    /**
     * A list of all Blahaj Plushies.
     */
    val BLAHAJ_PLUSHIES = create("blahaj_plushies")

    val ANEMONES_GENERATE_ON = create("anemones_generate_on")

    private fun create(id: String): TagKey<Block> {
        return TagKey.of(RegistryKeys.BLOCK, Identifier(HybridAquatic.MOD_ID, id))
    }
}
