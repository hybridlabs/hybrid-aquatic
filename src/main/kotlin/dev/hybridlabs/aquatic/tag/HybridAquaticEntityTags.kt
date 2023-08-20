package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticEntityTags {
    val BASKING_SHARK_PREY = create("prey/basking_shark")
    val BULL_SHARK_PREY = create("prey/bull_shark")
    val FRILLED_SHARK_PREY = create("prey/frilled_shark")
    val GREAT_WHITE_SHARK_PREY = create("prey/great_white_shark")
    val HAMMERHEAD_SHARK_PREY = create("prey/hammerhead_shark")
    val THRESHER_SHARK_PREY = create("prey/thresher_shark")
    val TIGER_SHARK_PREY = create("prey/tiger_shark")
    val WHALE_SHARK_PREY = create("prey/whale_shark")

    val LARGE_PREY = create("large_prey")
    val MEDIUM_PREY = create("medium_prey")
    val SMALL_PREY = create("small_prey")

    val DEEP_SEA_FISH = create("deep_sea_fish")
    val REEF_FISH = create("reef_fish")
    val RIVER_FISH = create("river_fish")
    val SWAMP_FISH = create("swamp_fish")
    val SALTWATER_FISH = create("saltwater_fish")
    val FRESHWATER_FISH = create("freshwater_fish")
    val SHALLOWS_FISH = create("shallows_fish")
    val OPEN_OCEAN_FISH = create("open_ocean_fish")
    val CRITTER = create("critter")

    private fun create(id: String): TagKey<EntityType<*>> {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id))
    }
}
