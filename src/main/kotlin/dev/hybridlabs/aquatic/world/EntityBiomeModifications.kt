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
        addFish(HybridAquaticEntityTypes.BETTA, HybridAquaticBiomeTags.SWAMPLAND, 1, 1, 1)
        addFish(HybridAquaticEntityTypes.GOLDFISH, HybridAquaticBiomeTags.CHERRY, 1, 1, 2)
        addFish(HybridAquaticEntityTypes.CARP, HybridAquaticBiomeTags.CHERRY, 1, 1, 2)
        addFish(HybridAquaticEntityTypes.STINGRAY, HybridAquaticBiomeTags.WARM_OCEANS, 3, 1, 2)
        addFish(HybridAquaticEntityTypes.MANTA_RAY, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.SURGEONFISH, HybridAquaticBiomeTags.WARM_OCEANS, 5, 1, 3)
        addFish(HybridAquaticEntityTypes.CLOWNFISH, HybridAquaticBiomeTags.REEF, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.PARROTFISH, HybridAquaticBiomeTags.REEF, 5, 1, 2)
        addFish(HybridAquaticEntityTypes.COWFISH, HybridAquaticBiomeTags.REEF, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.DISCUS, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.FLASHLIGHT_FISH, HybridAquaticBiomeTags.ALL_DEEP_OCEANS, 5, 1, 5)
        addFish(HybridAquaticEntityTypes.GOURAMI, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.LIONFISH, HybridAquaticBiomeTags.REEF, 3, 1, 2)
        addFish(HybridAquaticEntityTypes.MAHI, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 3)
        addFish(HybridAquaticEntityTypes.MORAY_EEL, HybridAquaticBiomeTags.REEF, 1, 1, 1)
        addFish(HybridAquaticEntityTypes.NEEDLEFISH, HybridAquaticBiomeTags.WARM_OCEANS, 3, 1, 3)
        addFish(HybridAquaticEntityTypes.OPAH, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.OSCAR, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 1, 1, 1)
        addFish(HybridAquaticEntityTypes.OARFISH, HybridAquaticBiomeTags.ALL_DEEP_OCEANS, 1, 1, 1)
        addFish(HybridAquaticEntityTypes.PIRANHA, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 5, 2, 5)
        addFish(HybridAquaticEntityTypes.ROCKFISH, HybridAquaticBiomeTags.OCEAN, 3, 1, 3)
        addFish(HybridAquaticEntityTypes.SEAHORSE, HybridAquaticBiomeTags.REEF, 1, 1, 2)
        addFish(HybridAquaticEntityTypes.STONEFISH, HybridAquaticBiomeTags.REEF, 1, 1, 1)
        addFish(HybridAquaticEntityTypes.SUNFISH, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 1, 1, 1)
        addFish(HybridAquaticEntityTypes.TETRA, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 3, 2, 3)
        addFish(HybridAquaticEntityTypes.TIGER_BARB, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 3, 2, 3)
        addFish(HybridAquaticEntityTypes.TOADFISH, HybridAquaticBiomeTags.WARM_OCEANS, 1, 1, 1)
        addFish(HybridAquaticEntityTypes.TRIGGERFISH, HybridAquaticBiomeTags.WARM_OCEANS, 3, 1, 1)
        addFish(HybridAquaticEntityTypes.TUNA, HybridAquaticBiomeTags.ALL_DEEP_OCEANS, 5, 1, 3)
        addFish(HybridAquaticEntityTypes.DANIO, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 3, 2, 3)


        // underground fishes
        addUndergroundFish(HybridAquaticEntityTypes.ANGLERFISH, HybridAquaticBiomeTags.ALL_OCEANS, 5, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.BARRELEYE, HybridAquaticBiomeTags.ALL_OCEANS, 3, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.DRAGONFISH, HybridAquaticBiomeTags.ALL_OCEANS, 5, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.FRILLED_SHARK, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.RATFISH, HybridAquaticBiomeTags.ALL_OCEANS, 5, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.SEA_ANGEL, HybridAquaticBiomeTags.DEEP_COLD_OCEANS, 3, 1, 1)


        // cephalopods
        addCephalopod(HybridAquaticEntityTypes.FIREFLY_SQUID, HybridAquaticBiomeTags.ALL_WARM_OCEANS, 3, 1, 2)
        addCephalopod(HybridAquaticEntityTypes.CUTTLEFISH, HybridAquaticBiomeTags.REEF, 3, 1, 1)

        // underground cephalopods
        addUndergroundCephalopod(HybridAquaticEntityTypes.VAMPIRE_SQUID, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addUndergroundCephalopod(HybridAquaticEntityTypes.NAUTILUS, HybridAquaticBiomeTags.ALL_OCEANS, 3, 1, 1)
        addUndergroundCephalopod(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS, HybridAquaticBiomeTags.ALL_OCEANS, 1, 1, 1)
        addUndergroundCephalopod(HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)

        // jellies
        addJelly(HybridAquaticEntityTypes.BARREL_JELLYFISH, HybridAquaticBiomeTags.ALL_OCEANS, 3, 1, 1)
        addJelly(HybridAquaticEntityTypes.MOON_JELLYFISH, HybridAquaticBiomeTags.WARM_OCEANS, 5, 1, 2)
        addJelly(HybridAquaticEntityTypes.MAUVE_STINGER, HybridAquaticBiomeTags.WARM_OCEANS, 3, 1, 2)
        addJelly(HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH, HybridAquaticBiomeTags.WARM_OCEANS, 3, 1, 1)
        addJelly(HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH, HybridAquaticBiomeTags.WARM_OCEANS, 3, 1, 1)
        addJelly(HybridAquaticEntityTypes.COMPASS_JELLYFISH, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addJelly(HybridAquaticEntityTypes.BLUE_JELLYFISH, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addJelly(HybridAquaticEntityTypes.SEA_NETTLE, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 2)
        addJelly(HybridAquaticEntityTypes.NOMURA_JELLYFISH, HybridAquaticBiomeTags.COLD_OCEANS, 1, 1, 1)
        addJelly(HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, HybridAquaticBiomeTags.DEEP_COLD_OCEANS, 1, 1, 1)

        // underground jellies
        addUndergroundJelly(HybridAquaticEntityTypes.ATOLLA_JELLYFISH, HybridAquaticBiomeTags.ALL_DEEP_OCEANS, 1, 1, 1)

        // sharks
        addShark(HybridAquaticEntityTypes.GREAT_WHITE_SHARK, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addShark(HybridAquaticEntityTypes.TIGER_SHARK, HybridAquaticBiomeTags.WARM_OCEANS, 3, 1, 2)
        addShark(HybridAquaticEntityTypes.HAMMERHEAD_SHARK, HybridAquaticBiomeTags.ALL_WARM_OCEANS, 5, 1, 2)
        addShark(HybridAquaticEntityTypes.THRESHER_SHARK, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 1)
        addShark(HybridAquaticEntityTypes.BULL_SHARK, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 3, 1, 2)
        addShark(HybridAquaticEntityTypes.WHALE_SHARK, HybridAquaticBiomeTags.DEEP_WARM_OCEANS, 1, 1, 1)
        addShark(HybridAquaticEntityTypes.BASKING_SHARK, HybridAquaticBiomeTags.COLD_OCEANS, 1, 1, 1)

        // crustaceans
        addCrustacean(HybridAquaticEntityTypes.DUNGENESS_CRAB, HybridAquaticBiomeTags.DUNGENESS_CRAB_SPAWN_BIOMES, 5, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.HERMIT_CRAB, HybridAquaticBiomeTags.TROPICAL_BEACHES, 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.FIDDLER_CRAB, HybridAquaticBiomeTags.SWAMPLAND, 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.GHOST_CRAB, HybridAquaticBiomeTags.GHOST_CRAB_SPAWN_BIOMES, 5, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.HORSESHOE_CRAB, HybridAquaticBiomeTags.ALL_WARM_OCEANS, 1, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.LIGHTFOOT_CRAB, HybridAquaticBiomeTags.LIGHTFOOT_CRAB_SPAWN_BIOMES, 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.FLOWER_CRAB, HybridAquaticBiomeTags.FLOWER_CRAB_SPAWN_BIOMES, 5, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.VAMPIRE_CRAB, HybridAquaticBiomeTags.TROPICAL_FRESHWATER, 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.SHRIMP, HybridAquaticBiomeTags.REEF, 5, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.CRAYFISH, HybridAquaticBiomeTags.RIVERS, 5, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.LOBSTER, HybridAquaticBiomeTags.REEF, 3, 1, 1)
        addCrustacean(HybridAquaticEntityTypes.COCONUT_CRAB, HybridAquaticBiomeTags.TROPICAL_BEACHES, 3, 1, 1)

        //underground crustaceans
        addUndergroundCrustacean(HybridAquaticEntityTypes.YETI_CRAB, HybridAquaticBiomeTags.DEEP_COLD_OCEANS, 2, 2, 2)
        addUndergroundCrustacean(HybridAquaticEntityTypes.GIANT_ISOPOD, HybridAquaticBiomeTags.ALL_DEEP_OCEANS, 1, 1, 1)
        addUndergroundCrustacean(HybridAquaticEntityTypes.SPIDER_CRAB, HybridAquaticBiomeTags.ALL_DEEP_OCEANS, 3, 1, 2)

        //critters
        addCritter(HybridAquaticEntityTypes.NUDIBRANCH, HybridAquaticBiomeTags.REEF, 3, 1, 2)
        addCritter(HybridAquaticEntityTypes.STARFISH, HybridAquaticBiomeTags.ALL_OCEANS, 3, 1, 3)
        addCritter(HybridAquaticEntityTypes.SEA_CUCUMBER, HybridAquaticBiomeTags.ALL_OCEANS, 3, 1, 3)
        addCritter(HybridAquaticEntityTypes.SEA_URCHIN, HybridAquaticBiomeTags.ALL_OCEANS, 5, 1, 3)
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

    private fun addCephalopod(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.CEPHALOPOD.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun addUndergroundCephalopod(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.CEPHALOPOD_UNDERGROUND.spawnGroup, weight, minGroup, maxGroup)
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

    private fun addUndergroundJelly(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.JELLY_UNDERGROUND.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun addCrustacean(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.CRUSTACEAN.spawnGroup, weight, minGroup, maxGroup)
    }

    private fun addUndergroundCrustacean(
        entityType: EntityType<*>,
        spawnTag: TagKey<Biome>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTag, HybridAquaticSpawnGroup.CRUSTACEAN_UNDERGROUND.spawnGroup, weight, minGroup, maxGroup)
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