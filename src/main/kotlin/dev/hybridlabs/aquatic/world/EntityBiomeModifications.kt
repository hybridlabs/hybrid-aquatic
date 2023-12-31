@file:Suppress("SameParameterValue")

package dev.hybridlabs.aquatic.world

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroup
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome

/**
 * Applies biome modifications for entities when initialised.
 */
object EntityBiomeModifications {
    init {
        // fish
        addFish(HybridAquaticEntityTypes.CLOWNFISH, HybridAquaticBiomeTags.CLOWNFISH_SPAWN_BIOMES, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.YELLOWFIN_TUNA, HybridAquaticBiomeTags.YELLOWFIN_SPAWN_BIOMES, 10, 1, 3)
        addFish(HybridAquaticEntityTypes.MAHIMAHI, HybridAquaticBiomeTags.MAHIMAHI_SPAWN_BIOMES, 10, 1, 3)
        addFish(HybridAquaticEntityTypes.BLUE_TANG, HybridAquaticBiomeTags.BLUE_TANG_SPAWN_BIOMES, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.COWFISH, HybridAquaticBiomeTags.COWFISH_SPAWN_BIOMES, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.SEAHORSE, HybridAquaticBiomeTags.SEAHORSE_SPAWN_BIOMES, 3, 1, 3)
        addFish(HybridAquaticEntityTypes.SUNFISH, HybridAquaticBiomeTags.SUNFISH_SPAWN_BIOMES, 3, 1, 2)
        addFish(HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY, HybridAquaticBiomeTags.BLUE_SPOTTED_STINGRAY_SPAWN_BIOMES, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.LIONFISH, HybridAquaticBiomeTags.LIONFISH_SPAWN_BIOMES, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.TOADFISH, HybridAquaticBiomeTags.TOADFISH_SPAWN_BIOMES, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.OARFISH, HybridAquaticBiomeTags.OARFISH_SPAWN_BIOMES, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.UNICORN_FISH, HybridAquaticBiomeTags.UNICORN_FISH_SPAWN_BIOMES, 10, 1, 2)
        addFish(HybridAquaticEntityTypes.STONEFISH, HybridAquaticBiomeTags.STONEFISH_SPAWN_BIOMES, 5, 1, 1)
        addFish(HybridAquaticEntityTypes.ROCKFISH, HybridAquaticBiomeTags.ROCKFISH_SPAWN_BIOMES, 10, 1, 2)
        addFish(HybridAquaticEntityTypes.MORAY_EEL, HybridAquaticBiomeTags.MORAY_EEL_SPAWN_BIOMES, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.FLASHLIGHT_FISH, HybridAquaticBiomeTags.FLASHLIGHT_FISH_SPAWN_BIOMES, 15, 1, 3)
        addFish(HybridAquaticEntityTypes.FIREFLY_SQUID, HybridAquaticBiomeTags.FIREFLY_SQUID_SPAWN_BIOMES, 10, 1, 3)
        addFish(HybridAquaticEntityTypes.OPAH, HybridAquaticBiomeTags.OPAH_SPAWN_BIOMES, 10, 1, 2)
        addFish(HybridAquaticEntityTypes.CUTTLEFISH, HybridAquaticBiomeTags.CUTTLEFISH_SPAWN_BIOMES, 10, 1, 1)
        addFish(HybridAquaticEntityTypes.TRIGGERFISH, HybridAquaticBiomeTags.TRIGGERFISH_SPAWN_BIOMES, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.TIGER_BARB, HybridAquaticBiomeTags.TIGER_BARB_SPAWN_BIOMES, 10, 2, 3)
        addFish(HybridAquaticEntityTypes.PIRANHA, HybridAquaticBiomeTags.PIRANHA_SPAWN_BIOMES, 10, 2, 3)
        addFish(HybridAquaticEntityTypes.RATFISH, HybridAquaticBiomeTags.RATFISH_SPAWN_BIOMES, 10, 1, 2)
        addFish(HybridAquaticEntityTypes.OSCAR, HybridAquaticBiomeTags.OSCAR_SPAWN_BIOMES, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.GOURAMI, HybridAquaticBiomeTags.GOURAMI_SPAWN_BIOMES, 15, 1, 2)
        addFish(HybridAquaticEntityTypes.ZEBRA_DANIO, HybridAquaticBiomeTags.ZEBRA_DANIO_SPAWN_BIOMES, 15, 2, 3)
        addFish(HybridAquaticEntityTypes.DISCUS, HybridAquaticBiomeTags.DISCUS_SPAWN_BIOMES, 15, 1, 2)
        addFish(HybridAquaticEntityTypes.BETTA, HybridAquaticBiomeTags.BETTA_SPAWN_BIOMES, 10, 1, 2)
        addFish(HybridAquaticEntityTypes.TETRA, HybridAquaticBiomeTags.TETRA_SPAWN_BIOMES, 15, 1, 2)
        addFish(HybridAquaticEntityTypes.NEEDLEFISH, HybridAquaticBiomeTags.NEEDLEFISH_SPAWN_BIOMES, 10, 1, 2)

        // underground fishes
        addUndergroundFish(HybridAquaticEntityTypes.ANGLERFISH, HybridAquaticBiomeTags.ANGLERFISH_SPAWN_BIOMES, 10, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.DRAGONFISH, HybridAquaticBiomeTags.DRAGONFISH_SPAWN_BIOMES, 3, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.BARRELEYE, HybridAquaticBiomeTags.BARRELEYE_SPAWN_BIOMES, 5, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.NAUTILUS, HybridAquaticBiomeTags.NAUTILUS_SPAWN_BIOMES, 3, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.SEA_ANGEL, HybridAquaticBiomeTags.SEA_ANGEL_SPAWN_BIOMES, 15, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.ATOLLA_JELLYFISH, HybridAquaticBiomeTags.ATOLLA_JELLYFISH_SPAWN_BIOMES, 3, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.FRILLED_SHARK, HybridAquaticBiomeTags.FRILLED_SHARK_SPAWN_BIOMES, 5, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.VAMPIRE_SQUID, HybridAquaticBiomeTags.VAMPIRE_SQUID_SPAWN_BIOMES, 5, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, HybridAquaticBiomeTags.GLOWING_SUCKER_OCTOPUS_SPAWN_BIOMES, 5, 1, 1)

        // jellies
        addJelly(HybridAquaticEntityTypes.MOON_JELLYFISH, HybridAquaticBiomeTags.MOON_JELLYFISH_SPAWN_BIOMES, 10, 1, 4)
        addJelly(HybridAquaticEntityTypes.SEA_NETTLE, HybridAquaticBiomeTags.SEA_NETTLE_SPAWN_BIOMES, 7, 1, 3)
        addJelly(HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH, HybridAquaticBiomeTags.FRIED_EGG_JELLYFISH_SPAWN_BIOMES, 1, 1, 2)
        addJelly(HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH, HybridAquaticBiomeTags.CAULIFLOWER_JELLYFISH_SPAWN_BIOMES, 1, 1, 2)
        addJelly(HybridAquaticEntityTypes.NOMURA_JELLYFISH, HybridAquaticBiomeTags.NOMURA_JELLYFISH_SPAWN_BIOMES, 5, 1, 1)
        addJelly(HybridAquaticEntityTypes.BARREL_JELLYFISH, HybridAquaticBiomeTags.BARREL_JELLYFISH_SPAWN_BIOMES, 7, 1, 2)
        addJelly(HybridAquaticEntityTypes.COMPASS_JELLYFISH, HybridAquaticBiomeTags.COMPASS_JELLYFISH_SPAWN_BIOMES, 7, 1, 2)
        addJelly(HybridAquaticEntityTypes.BLUE_JELLYFISH, HybridAquaticBiomeTags.BLUE_JELLYFISH_SPAWN_BIOMES, 7, 1, 2)
        addJelly(HybridAquaticEntityTypes.MAUVE_STINGER, HybridAquaticBiomeTags.MAUVE_STINGER_SPAWN_BIOMES, 7, 1, 3)
        addJelly(HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, HybridAquaticBiomeTags.LIONS_MANE_JELLYFISH_SPAWN_BIOMES, 3, 1, 1)

        // sharks
        addShark(HybridAquaticEntityTypes.GREAT_WHITE_SHARK, HybridAquaticBiomeTags.GREAT_WHITE_SHARK_SPAWN_BIOMES, 7, 1, 1)
        addShark(HybridAquaticEntityTypes.TIGER_SHARK, HybridAquaticBiomeTags.TIGER_SHARK_SPAWN_BIOMES, 7, 1, 1)
        addShark(HybridAquaticEntityTypes.HAMMERHEAD_SHARK, HybridAquaticBiomeTags.HAMMERHEAD_SHARK_SPAWN_BIOMES, 10, 1, 1)
        addShark(HybridAquaticEntityTypes.THRESHER_SHARK, HybridAquaticBiomeTags.THRESHER_SHARK_SPAWN_BIOMES, 10, 1, 1)
        addShark(HybridAquaticEntityTypes.BULL_SHARK, HybridAquaticBiomeTags.BULL_SHARK_SPAWN_BIOMES, 7, 1, 1)
        addShark(HybridAquaticEntityTypes.WHALE_SHARK, HybridAquaticBiomeTags.WHALE_SHARK_SPAWN_BIOMES, 7, 1, 1)
        addShark(HybridAquaticEntityTypes.BASKING_SHARK, HybridAquaticBiomeTags.BASKING_SHARK_SPAWN_BIOMES, 5, 1, 1)

        // critters
        addCritter(HybridAquaticEntityTypes.CRAB, HybridAquaticBiomeTags.CRAB_SPAWN_BIOMES, 10, 1, 2)
        addCritter(HybridAquaticEntityTypes.FIDDLER_CRAB, HybridAquaticBiomeTags.FIDDLER_CRAB_SPAWN_BIOMES, 7, 1, 2)
        addCritter(HybridAquaticEntityTypes.HERMIT_CRAB, HybridAquaticBiomeTags.HERMIT_CRAB_SPAWN_BIOMES, 7, 1, 2)
        addCritter(HybridAquaticEntityTypes.STARFISH, HybridAquaticBiomeTags.STARFISH_SPAWN_BIOMES, 7, 1, 3)
        addCritter(HybridAquaticEntityTypes.NUDIBRANCH, HybridAquaticBiomeTags.NUDIBRANCH_SPAWN_BIOMES, 5, 1, 2)
        addCritter(HybridAquaticEntityTypes.SEA_CUCUMBER, HybridAquaticBiomeTags.SEA_CUCUMBER_SPAWN_BIOMES, 7, 1, 3)
        addCritter(HybridAquaticEntityTypes.SEA_URCHIN, HybridAquaticBiomeTags.SEA_URCHIN_SPAWN_BIOMES, 7, 1, 3)
        addCritter(HybridAquaticEntityTypes.GIANT_CLAM, HybridAquaticBiomeTags.GIANT_CLAM_SPAWN_BIOMES, 7, 1, 2)
    }

    private fun addFish(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.FISH.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun addUndergroundFish(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.FISH_UNDERGROUND.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun addShark(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.SHARK.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun addJelly(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.JELLY.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun addCritter(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.CRITTER.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun add(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        spawnGroup: SpawnGroup,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        BiomeModifications.addSpawn({ it.hasTag(spawnTag) }, spawnGroup, entityType, weight, minGroup, maxGroup)
    }
}
