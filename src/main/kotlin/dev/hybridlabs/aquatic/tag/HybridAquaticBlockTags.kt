package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticBlockTags {

    val PLUSHIES = create("plushies")

    val URCHIN_BREAKABLES = create("urchin_breakables")

    val ANEMONES_GENERATE_IN = create("anemones_generate_in")

    val GIANT_CLAM_GENERATE_IN = create("giant_clam_generate_in")

    val TUBE_SPONGE_GENERATE_IN = create("tube_sponge_generate_in")

    val MESSAGE_IN_A_BOTTLE_SPAWNS_IN = create("message_in_a_bottle_spawns_in")

    private fun create(id: String): TagKey<Block> {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(HybridAquatic.MOD_ID, id))
    }
}
