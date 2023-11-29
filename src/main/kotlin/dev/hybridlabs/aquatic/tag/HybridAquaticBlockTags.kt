package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticBlockTags {
    /**
     * A list of all Plushies.
     */
    val PLUSHIES = create("plushies")

    /**
     * A list of blocks that anemones can generate inside.
     */
    val ANEMONES_GENERATE_IN = create("anemones_generate_in")

    /**
     * A list of blocks that living sponges can generate inside.
     */
    val TUBE_SPONGE_GENERATE_IN = create("tube_sponge_generate_in")


    /**
     * A list of blocks that hydrothermal vents can generate inside.
     */
    val HYDROTHERMAL_VENT_GENERATE_IN = create("hydrothermal_vent_generate_in")

    /**
     * A list of blocks that message in a bottles can generate inside.
     */
    val MESSAGE_IN_A_BOTTLE_SPAWNS_IN = create("message_in_a_bottle_spawns_in")

    /**
     * A list of blocks that Crab can dig up.
     */
    val CRAB_DIGGABLE_BLOCKS = create("crab_diggable_blocks")

    /**
     * A list of blocks that will drop items when crabs try to dig them.
     */
    val CRAB_DIGGABLE_TREASURE_BLOCKS = create("crab_diggable_treasure_blocks")

    /**
     * A list of blocks where crabs can spawn.
     */
    val CRABS_SPAWN_ON = create("crabs_spawn_on")

    private fun create(id: String): TagKey<Block> {
        return TagKey.of(RegistryKeys.BLOCK, Identifier(HybridAquatic.MOD_ID, id))
    }
}
