package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.entity.EntityType
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object HybridAquaticEntityTags {
    val NONE = create("prey/none")

    val LARGE_PREY = create("large_prey")
    val MEDIUM_PREY = create("medium_prey")
    val SMALL_PREY = create("small_prey")

    val CRITTER = create("critter")
    val CRUSTACEAN = create("crustacean")
    val CEPHALOPOD = create("cephalopod")
    val JELLYFISH = create("jellyfish")
    val FISH = create("fish")
    val SHARK = create("sharks")

    val CAN_USE_FISHING_NET_ON = create("can_use_fishing_net_on")

    private fun create(id: String): TagKey<EntityType<*>> {
        return TagKey.of(Registry.ENTITY_TYPE_KEY, Identifier(HybridAquatic.MOD_ID, id))
    }
}
