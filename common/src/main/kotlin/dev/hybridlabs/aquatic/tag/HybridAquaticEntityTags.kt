package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType

object HybridAquaticEntityTags {
    //#region Food Chain tags
    val BAIT_FISH = create("bait_fish")
    val SMALL_CREATURES = create("small_creatures")
    val MEDIUM_CREATURES = create("medium_creatures")
    val LARGE_CREATURES = create("large_creatures")
    val TOXIC_ANIMALS = create("toxic_animals")
    val OTTER_PREY = create("otter_prey")

    //#region Crustacean Tags
    val ALL_CRUSTACEANS = create("all_crustaceans")
    val CRAB = create("crab")
    val LOBSTER = create("lobster")
    val SHRIMP = create("shrimp")

    //#region Cephalopod Tags
    val ALL_CEPHALOPODS = create("all_cephalopods")
    val OCTOPUS = create("octopus")
    val SQUID = create("squid")

    //#region Fish Tags
    val ALL_FISH = create("all_fish")
    val RAY = create("ray")
    val REEF_FISH = create("reef_fish")
    val DEEP_FISH = create("deep_fish")
    val RIVER_FISH = create("river_fish")
    val TROPICAL_RIVER_FISH = create("tropical_river_fish")
    val SWAMP_FISH = create("swamp_fish")
    val MANGROVE_FISH = create("mangrove_fish")

    //#region Shark Tags
    val ALL_SHARKS = create("all_sharks")
    val SMALL_SHARK = create("small_shark")
    val MEDIUM_SHARK = create("medium_shark")
    val LARGE_SHARK = create("large_shark")

    //#region Mammal Tags
    val ALL_MAMMALS = create("all_mammals")
    val SIRENIAN = create("sirenian")
    val SEAL = create("seal")
    val DOLPHIN = create("dolphin")
    val WHALE = create("whale")

    //#region Misc Creature Tags
    val TURTLE = create("turtle")
    val ALL_CRITTERS = create("all_critters")
    val ALL_JELLYFISH = create("all_jellyfish")

    val CAN_USE_FISHING_NET_ON = create("can_use_fishing_net_on")

    private fun create(id: String): TagKey<EntityType<*>> {
        return TagKey.create(Registries.ENTITY_TYPE, CommonClass.locate(id))
    }
}
