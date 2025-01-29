package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnLocation
import net.minecraft.entity.SpawnLocationTypes
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.SpawnRestriction.SpawnPredicate
import net.minecraft.entity.mob.HostileEntity
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
            HybridAquaticEntityTypes.TUNA,
            HybridAquaticEntityTypes.MAHI,
            HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticEntityTypes.COWFISH,
            HybridAquaticEntityTypes.SEAHORSE,
            HybridAquaticEntityTypes.SUNFISH,
            HybridAquaticEntityTypes.LIONFISH,
            HybridAquaticEntityTypes.TOADFISH,
            HybridAquaticEntityTypes.OARFISH,
            HybridAquaticEntityTypes.STONEFISH,
            HybridAquaticEntityTypes.ROCKFISH,
            HybridAquaticEntityTypes.MORAY_EEL,
            HybridAquaticEntityTypes.FLASHLIGHT_FISH,
            HybridAquaticEntityTypes.OPAH,
            HybridAquaticEntityTypes.TRIGGERFISH,
            HybridAquaticEntityTypes.PARROTFISH,
            HybridAquaticEntityTypes.TIGER_BARB,
            HybridAquaticEntityTypes.PIRANHA,
            HybridAquaticEntityTypes.OSCAR,
            HybridAquaticEntityTypes.GOURAMI,
            HybridAquaticEntityTypes.DANIO,
            HybridAquaticEntityTypes.DISCUS,
            HybridAquaticEntityTypes.GOLDFISH,
            HybridAquaticEntityTypes.BETTA,
            HybridAquaticEntityTypes.TETRA,
            HybridAquaticEntityTypes.NEEDLEFISH,
            HybridAquaticEntityTypes.CARP,
        ).forEach { registerFish(it) }

        // underground fish
        setOf(
            HybridAquaticEntityTypes.ANGLERFISH,
            HybridAquaticEntityTypes.DRAGONFISH,
            HybridAquaticEntityTypes.BARRELEYE,
            HybridAquaticEntityTypes.SEA_ANGEL,
            HybridAquaticEntityTypes.FRILLED_SHARK,
            HybridAquaticEntityTypes.RATFISH,
            ).forEach { registerFishUnderground(it) }

        // rays
        setOf(
            HybridAquaticEntityTypes.STINGRAY,
            HybridAquaticEntityTypes.MANTA_RAY,
        ).forEach { registerRay(it) }

        // squids
        setOf(
            HybridAquaticEntityTypes.FIREFLY_SQUID,
            HybridAquaticEntityTypes.CUTTLEFISH,
        ).forEach { registerCephalopod(it) }

        // underground squids
        setOf(
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
            HybridAquaticEntityTypes.NAUTILUS,
            HybridAquaticEntityTypes.VAMPIRE_SQUID,
            HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
        ).forEach { registerCephalopodUnderground(it) }

        // jellies
        setOf(
            HybridAquaticEntityTypes.MOON_JELLYFISH,
            HybridAquaticEntityTypes.MAUVE_STINGER,
            HybridAquaticEntityTypes.SEA_NETTLE,
            HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
            HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
            HybridAquaticEntityTypes.BLUE_JELLYFISH,
            HybridAquaticEntityTypes.COMPASS_JELLYFISH,
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
            HybridAquaticEntityTypes.NOMURA_JELLYFISH,
            HybridAquaticEntityTypes.BARREL_JELLYFISH,
        ).forEach { registerJelly(it) }

        setOf(
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
        ).forEach { registerJellyUnderground(it) }

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
            HybridAquaticEntityTypes.STARFISH,
            HybridAquaticEntityTypes.NUDIBRANCH,
            HybridAquaticEntityTypes.SEA_CUCUMBER,
            HybridAquaticEntityTypes.SEA_URCHIN,
        ).forEach { registerCritter(it) }

        // crustaceans
        setOf(
            HybridAquaticEntityTypes.DUNGENESS_CRAB,
            HybridAquaticEntityTypes.FIDDLER_CRAB,
            HybridAquaticEntityTypes.HERMIT_CRAB,
            HybridAquaticEntityTypes.GHOST_CRAB,
            HybridAquaticEntityTypes.FLOWER_CRAB,
            HybridAquaticEntityTypes.VAMPIRE_CRAB,
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
            HybridAquaticEntityTypes.HORSESHOE_CRAB,
            HybridAquaticEntityTypes.COCONUT_CRAB,
            HybridAquaticEntityTypes.SHRIMP,
            HybridAquaticEntityTypes.CRAYFISH,
            HybridAquaticEntityTypes.LOBSTER,
        ).forEach { registerCrustacean(it) }

        setOf(
            HybridAquaticEntityTypes.YETI_CRAB,
            HybridAquaticEntityTypes.SPIDER_CRAB,
            HybridAquaticEntityTypes.GIANT_ISOPOD
        ).forEach { registerCrustaceanUnderground(it) }

        setOf(
            HybridAquaticEntityTypes.KARKINOS,
        ).forEach { registerMiniboss(it) }
    }

    private fun <T : WaterCreatureEntity> registerFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerFishUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canUndergroundSpawn)
    }

    private fun <T : WaterCreatureEntity> registerRay(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticRayEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCephalopod(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCephalopodEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCephalopodUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCephalopodEntity::canUndergroundSpawn)
    }

    private fun <T : WaterCreatureEntity> registerShark(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerJellyUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity::canUndergroundSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrustaceanEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCrustaceanUnderground(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrustaceanEntity::canUndergroundSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCritter(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCritterEntity::canSpawn)
    }

    private fun <T : HostileEntity> registerMiniboss(entityType: EntityType<T>) {
        registerMiniboss(entityType, HybridAquaticMinibossEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerWaterCreature(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            SpawnLocationTypes.IN_WATER,
            predicate
        )
    }

    private fun <T : HostileEntity> registerMiniboss(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            SpawnLocationTypes.IN_WATER,
            predicate
        )
    }

    private fun <T : WaterCreatureEntity> registerLandWaterCreature(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        register(
            entityType,
            SpawnLocationTypes.UNRESTRICTED,
            predicate
        )
    }

    private fun <T : MobEntity> register(entityType: EntityType<T>, location: SpawnLocation, predicate: SpawnPredicate<T>) {
        SpawnRestriction.register(entityType, location, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
