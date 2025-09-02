package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType

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
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation(HybridAquatic.MOD_ID, id))
    }
}
