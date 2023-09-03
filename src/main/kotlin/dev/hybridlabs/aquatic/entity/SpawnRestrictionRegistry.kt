package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.fish.AnglerfishEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnRestriction
import net.minecraft.entity.SpawnRestriction.SpawnPredicate
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
            HybridAquaticEntityTypes.SEA_ANGEL,
            HybridAquaticEntityTypes.STONEFISH,
            HybridAquaticEntityTypes.ROCKFISH,
            HybridAquaticEntityTypes.MORAY_EEL,
            HybridAquaticEntityTypes.FLASHLIGHT_FISH,
            HybridAquaticEntityTypes.FIREFLY_SQUID,
            HybridAquaticEntityTypes.OPAH,
            HybridAquaticEntityTypes.CUTTLEFISH,
            HybridAquaticEntityTypes.VAMPIRE_SQUID,
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
            HybridAquaticEntityTypes.TRIGGERFISH,
            HybridAquaticEntityTypes.TIGER_BARB,
            HybridAquaticEntityTypes.PIRANHA,
            HybridAquaticEntityTypes.RATFISH,
            HybridAquaticEntityTypes.OSCAR,
            HybridAquaticEntityTypes.GOURAMI,
            HybridAquaticEntityTypes.BARRELEYE,
            HybridAquaticEntityTypes.ZEBRA_DANIO,
            HybridAquaticEntityTypes.DISCUS,
            HybridAquaticEntityTypes.BETTA,
            HybridAquaticEntityTypes.NAUTILUS,
            HybridAquaticEntityTypes.TETRA,
            HybridAquaticEntityTypes.NEEDLEFISH,
            HybridAquaticEntityTypes.DRAGONFISH,
        ).forEach { registerFish(it) }

        // anglerfish
        registerWaterCreature(HybridAquaticEntityTypes.ANGLERFISH, AnglerfishEntity::canSpawn)

        // jellies
        registerJelly(HybridAquaticEntityTypes.MOON_JELLYFISH)
        registerJelly(HybridAquaticEntityTypes.SEA_NETTLE)
        registerJelly(HybridAquaticEntityTypes.FRIED_EGG_JELLY)
        registerJelly(HybridAquaticEntityTypes.CAULIFLOWER_JELLY)
        registerJelly(HybridAquaticEntityTypes.NOMURA_JELLY)


        // sharks
        setOf(
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
            HybridAquaticEntityTypes.TIGER_SHARK,
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
            HybridAquaticEntityTypes.FRILLED_SHARK,
            HybridAquaticEntityTypes.THRESHER_SHARK,
            HybridAquaticEntityTypes.BULL_SHARK,
            HybridAquaticEntityTypes.WHALE_SHARK,
            HybridAquaticEntityTypes.BASKING_SHARK,
        ).forEach { registerShark(it) }

        // critters
        setOf(
            HybridAquaticEntityTypes.CRAB,
            HybridAquaticEntityTypes.FIDDLER_CRAB,
            HybridAquaticEntityTypes.HERMIT_CRAB,
            HybridAquaticEntityTypes.STARFISH,
            HybridAquaticEntityTypes.NUDIBRANCH,
            HybridAquaticEntityTypes.SEA_CUCUMBER,
            HybridAquaticEntityTypes.SEA_URCHIN,
            HybridAquaticEntityTypes.GIANT_CLAM,
        ).forEach { registerCritter(it) }
    }

    private fun <T : WaterCreatureEntity> registerFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerShark(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerCritter(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCritterEntity::canSpawn)
    }

    private fun <T : WaterCreatureEntity> registerWaterCreature(entityType: EntityType<T>, predicate: SpawnPredicate<T>) {
        SpawnRestriction.register(
            entityType,
            SpawnRestriction.Location.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            predicate
        )
    }
}
