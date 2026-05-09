package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object HABlockTags {

    val PLUSHIES = create("plushies")

    val KELP = create("kelp")

    val ANEMONES = create("anemones")

    val ALGIVORE_EDIBLE = create("algivore_edible")
    val CORALLIVORE_EDIBLE = create("corallivore_edible")
    val HERBIVORE_EDIBLE = create("herbivore_edible")

    val DEEP_CORALS = create("deep_corals")
    val DEEP_CORAL_PLANTS = create("deep_coral_plants")
    val DEEP_WALL_CORALS = create("deep_wall_corals")
    val DEEP_CORAL_BLOCKS = create("deep_coral_blocks")

    val BLEACHED_CORALS = create("bleached_corals")
    val BLEACHED_CORAL_PLANTS = create("bleached_coral_plants")
    val BLEACHED_WALL_CORALS = create("bleached_wall_corals")
    val BLEACHED_CORAL_BLOCKS = create("bleached_coral_blocks")

    val TIDE_POOL_REPLACEABLE = create("tide_pool_replaceable")

    val CORAL_MOUND_BLOCKS = create("coral_mound_blocks")
    val CORAL_MOUND_BASE_BLOCKS = create("coral_mound_base_blocks")
    val MOUND_BLOCKS = create("mound_blocks")

    val IS_HONEYLIKE = create("is_honeyblocklike")
    val IS_SLIMELIKE = create("is_slimeblocklike")

    private fun create(id: String): TagKey<Block> {
        return TagKey.create(Registries.BLOCK, CommonClass.locate(id))
    }
}
