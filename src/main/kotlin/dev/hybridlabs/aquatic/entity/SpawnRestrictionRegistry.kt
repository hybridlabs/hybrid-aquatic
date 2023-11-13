package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCrabEntity
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.SpawnRestriction.SpawnPredicate
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.Heightmap

/**
 * Registers spawn restrictions for all entities when initialised.
 */
object SpawnRestrictionRegistry {
    init {
        // fish
        setOf(
            HybridAquaticEntityTypes.CLOWNFISH,
            HybridAquaticEntityTypes.YELLOWFIN_TUNA,
            HybridAquaticEntityTypes.MAHIMAHI,
            HybridAquaticEntityTypes.BLUE_TANG,
            HybridAquaticEntityTypes.COWFISH,
            HybridAquaticEntityTypes.SEAHORSE,
            HybridAquaticEntityTypes.SUNFISH,
            HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY,
            HybridAquaticEntityTypes.LIONFISH,
            HybridAquaticEntityTypes.TOADFISH,
            HybridAquaticEntityTypes.OARFISH,
            HybridAquaticEntityTypes.UNICORN_FISH,
            HybridAquaticEntityTypes.STONEFISH,
            HybridAquaticEntityTypes.ROCKFISH,
            HybridAquaticEntityTypes.MORAY_EEL,
            HybridAquaticEntityTypes.FLASHLIGHT_FISH,
            HybridAquaticEntityTypes.FIREFLY_SQUID,
            HybridAquaticEntityTypes.OPAH,
            HybridAquaticEntityTypes.CUTTLEFISH,
            HybridAquaticEntityTypes.TRIGGERFISH,
            HybridAquaticEntityTypes.TIGER_BARB,
            HybridAquaticEntityTypes.PIRANHA,
            HybridAquaticEntityTypes.RATFISH,
            HybridAquaticEntityTypes.OSCAR,
            HybridAquaticEntityTypes.GOURAMI,
            HybridAquaticEntityTypes.ZEBRA_DANIO,
            HybridAquaticEntityTypes.DISCUS,
            HybridAquaticEntityTypes.BETTA,
            HybridAquaticEntityTypes.TETRA,
            HybridAquaticEntityTypes.NEEDLEFISH,
        ).forEach { registerFish(it) }

        // underground fish
        setOf(
            HybridAquaticEntityTypes.ANGLERFISH,
            HybridAquaticEntityTypes.DRAGONFISH,
            HybridAquaticEntityTypes.BARRELEYE,
            HybridAquaticEntityTypes.NAUTILUS,
            HybridAquaticEntityTypes.SEA_ANGEL,
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
            HybridAquaticEntityTypes.FRILLED_SHARK,
            HybridAquaticEntityTypes.VAMPIRE_SQUID,
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
        ).forEach { registerFishUnderground(it) }

        // jellies
        setOf(
            HybridAquaticEntityTypes.MOON_JELLYFISH,
            HybridAquaticEntityTypes.SEA_NETTLE,
            HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
            HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
            HybridAquaticEntityTypes.NOMURA_JELLYFISH,
        ).forEach { registerJelly(it) }

        // sharks
        setOf(
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
            HybridAquaticEntityTypes.TIGER_SHARK,
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
            HybridAquaticEntityTypes.THRESHER_SHARK,
            HybridAquaticEntityTypes.BULL_SHARK,
            HybridAquaticEntityTypes.WHALE_SHARK,
            HybridAquaticEntityTypes.BASKING_SHARK,
        ).forEach { registerShark(it) }

        // critters
        setOf(

            HybridAquaticEntityTypes.GIANT_ISOPOD,
            HybridAquaticEntityTypes.SHRIMP,
            HybridAquaticEntityTypes.MANTIS_SHRIMP,
            HybridAquaticEntityTypes.CRAYFISH,
            HybridAquaticEntityTypes.LOBSTER,
            HybridAquaticEntityTypes.STARFISH,
            HybridAquaticEntityTypes.NUDIBRANCH,
            HybridAquaticEntityTypes.SEA_CUCUMBER,
            HybridAquaticEntityTypes.SEA_URCHIN,
            HybridAquaticEntityTypes.GIANT_CLAM,
        ).forEach { registerCritter(it) }

        // crabs
        setOf(
            HybridAquaticEntityTypes.CRAB,
            HybridAquaticEntityTypes.FIDDLER_CRAB,
            HybridAquaticEntityTypes.HERMIT_CRAB,
            HybridAquaticEntityTypes.GHOST_CRAB,
            HybridAquaticEntityTypes.FLOWER_CRAB,
            HybridAquaticEntityTypes.VAMPIRE_CRAB,
            HybridAquaticEntityTypes.SPIDER_CRAB,
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
            HybridAquaticEntityTypes.HORSESHOE_CRAB,
            HybridAquaticEntityTypes.COCONUT_CRAB,
        ).forEach { registerCrab(it) }
    }

    private fun <T : WaterCreatureEntity> registerFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerFishUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canUndergroundSpawn)
    }

    private fun <T : WaterCreatureEntity> registerShark(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCrab(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrabEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCritter(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCritterEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerWaterCreature(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            SpawnRestriction.Location.IN_WATER,
            predicate
        )
    }

    private fun <T : WaterCreatureEntity> registerLandWaterCreature(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            SpawnRestriction.Location.NO_RESTRICTIONS,
            predicate
        )
    }

    private fun <T : MobEntity> register(entityType: EntityType<T>, location: SpawnRestriction.Location, predicate: SpawnPredicate<T>) {
        SpawnRestriction.register(entityType, location, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
