package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object HybridAquaticBlockTags {

    val PLUSHIES = create("plushies")

    val URCHIN_BREAKABLES = create("urchin_breakables")

    val ANEMONES_GENERATE_IN = create("anemones_generate_in")

    val CLOWNFISH_ANEMONES = create("clownfish_anemones")

    val GIANT_CLAM_GENERATE_IN = create("giant_clam_generate_in")

    val TUBE_SPONGE_GENERATE_IN = create("tube_sponge_generate_in")

    val MESSAGE_IN_A_BOTTLE_SPAWNS_IN = create("message_in_a_bottle_spawns_in")

    val TIDE_POOL_REPLACEABLE = create("tide_pool_replaceable")

    private fun create(id: String): TagKey<Block> {
        return TagKey.create(Registries.BLOCK, CommonClass.locate(id))
    }
}
