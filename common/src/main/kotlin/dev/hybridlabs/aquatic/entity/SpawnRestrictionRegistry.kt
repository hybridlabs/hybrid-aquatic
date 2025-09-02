package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.levelgen.Heightmap

/**
 * Registers spawn restrictions for all entities when initialised.
 */
object SpawnRestrictionRegistry {
    fun registerSpawnRestrictions() {
        // fish
        setOf(
            HybridAquaticEntityTypes.AFRICAN_BUTTERFLY.get(),
            HybridAquaticEntityTypes.FLYING_FISH.get(),
            HybridAquaticEntityTypes.DAMSELFISH.get(),
            HybridAquaticEntityTypes.CLOWNFISH.get(),
            HybridAquaticEntityTypes.TUNA.get(),
            HybridAquaticEntityTypes.MAHI.get(),
            HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticEntityTypes.BOXFISH.get(),
            HybridAquaticEntityTypes.SEAHORSE.get(),
            HybridAquaticEntityTypes.SUNFISH.get(),
            HybridAquaticEntityTypes.LIONFISH.get(),
            HybridAquaticEntityTypes.TOADFISH.get(),
            HybridAquaticEntityTypes.STONEFISH.get(),
            HybridAquaticEntityTypes.ROCKFISH.get(),
            HybridAquaticEntityTypes.SEA_BASS.get(),
            HybridAquaticEntityTypes.MORAY_EEL.get(),
            HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(),
            HybridAquaticEntityTypes.SQUIRRELFISH.get(),
            HybridAquaticEntityTypes.OPAH.get(),
            HybridAquaticEntityTypes.TRIGGERFISH.get(),
            HybridAquaticEntityTypes.PARROTFISH.get(),
            HybridAquaticEntityTypes.TIGER_BARB.get(),
            HybridAquaticEntityTypes.PIRANHA.get(),
            HybridAquaticEntityTypes.OSCAR.get(),
            HybridAquaticEntityTypes.GOURAMI.get(),
            HybridAquaticEntityTypes.DANIO.get(),
            HybridAquaticEntityTypes.DISCUS.get(),
            HybridAquaticEntityTypes.GOLDFISH.get(),
            HybridAquaticEntityTypes.BETTA.get(),
            HybridAquaticEntityTypes.TETRA.get(),
            HybridAquaticEntityTypes.NEEDLEFISH.get(),
            HybridAquaticEntityTypes.MACKEREL.get(),
            HybridAquaticEntityTypes.CARP.get(),
            HybridAquaticEntityTypes.STINGRAY.get(),
            HybridAquaticEntityTypes.MANTA_RAY.get(),
            HybridAquaticEntityTypes.GOLDEN_DORADO.get(),
            HybridAquaticEntityTypes.PEARLFISH.get(),
        ).forEach { registerFish(it) }

        // underground fish
        setOf(
            HybridAquaticEntityTypes.ANGLERFISH.get(),
            HybridAquaticEntityTypes.BARRELEYE.get(),
            HybridAquaticEntityTypes.COELACANTH.get(),
            HybridAquaticEntityTypes.DRAGONFISH.get(),
            HybridAquaticEntityTypes.OARFISH.get(),
            HybridAquaticEntityTypes.RATFISH.get(),
            HybridAquaticEntityTypes.JOHN_DORY.get(),
            HybridAquaticEntityTypes.SNAILFISH.get(),
            HybridAquaticEntityTypes.SEA_ANGEL.get(),
        ).forEach { registerFishUnderground(it) }

        // cephalopods
        setOf(
            HybridAquaticEntityTypes.ARROW_SQUID.get(),
            HybridAquaticEntityTypes.FIREFLY_SQUID.get(),
            HybridAquaticEntityTypes.CUTTLEFISH.get(),
        ).forEach { registerCephalopod(it) }

        // underground cephalopods
        setOf(
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS.get(),
            HybridAquaticEntityTypes.NAUTILUS.get(),
            HybridAquaticEntityTypes.VAMPIRE_SQUID.get(),
            HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(),
        ).forEach { registerCephalopodUnderground(it) }

        // jellies
        setOf(
            HybridAquaticEntityTypes.MOON_JELLYFISH.get(),
            HybridAquaticEntityTypes.SEA_NETTLE.get(),
            HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH.get(),
            HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH.get(),
            HybridAquaticEntityTypes.BLUE_JELLYFISH.get(),
            HybridAquaticEntityTypes.COMPASS_JELLYFISH.get(),
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get(),
            HybridAquaticEntityTypes.NOMURA_JELLYFISH.get(),
            HybridAquaticEntityTypes.BARREL_JELLYFISH.get(),
            HybridAquaticEntityTypes.BOX_JELLYFISH.get(),
        ).forEach { registerJelly(it) }

        setOf(
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH.get(),
            HybridAquaticEntityTypes.BIG_RED_JELLYFISH.get(),
            HybridAquaticEntityTypes.COSMIC_JELLYFISH.get(),
            HybridAquaticEntityTypes.FIREWORK_JELLYFISH.get(),
            HybridAquaticEntityTypes.MAUVE_STINGER.get(),
        ).forEach { registerJellyUnderground(it) }

        // sharks
        setOf(
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(),
            HybridAquaticEntityTypes.TIGER_SHARK.get(),
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get(),
            HybridAquaticEntityTypes.THRESHER_SHARK.get(),
            HybridAquaticEntityTypes.BULL_SHARK.get(),
            HybridAquaticEntityTypes.WHALE_SHARK.get(),
            HybridAquaticEntityTypes.BASKING_SHARK.get(),
        ).forEach { registerShark(it) }

        setOf(
            HybridAquaticEntityTypes.FRILLED_SHARK.get(),
            HybridAquaticEntityTypes.LANTERN_SHARK.get(),
        ).forEach { registerSharkUnderground(it) }

        // critters
        setOf(
            HybridAquaticEntityTypes.STARFISH.get(),
            HybridAquaticEntityTypes.NUDIBRANCH.get(),
            HybridAquaticEntityTypes.SEA_CUCUMBER.get(),
            HybridAquaticEntityTypes.SEA_URCHIN.get(),
        ).forEach { registerCritter(it) }

        // crustaceans
        setOf(
            HybridAquaticEntityTypes.DUNGENESS_CRAB.get(),
            HybridAquaticEntityTypes.FIDDLER_CRAB.get(),
            HybridAquaticEntityTypes.HERMIT_CRAB.get(),
            HybridAquaticEntityTypes.GHOST_CRAB.get(),
            HybridAquaticEntityTypes.FLOWER_CRAB.get(),
            HybridAquaticEntityTypes.VAMPIRE_CRAB.get(),
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get(),
            HybridAquaticEntityTypes.HORSESHOE_CRAB.get(),
            HybridAquaticEntityTypes.COCONUT_CRAB.get(),
            HybridAquaticEntityTypes.DECORATOR_CRAB.get(),
            HybridAquaticEntityTypes.SHRIMP.get(),
            HybridAquaticEntityTypes.CRAYFISH.get(),
            HybridAquaticEntityTypes.LOBSTER.get(),
        ).forEach { registerCrustacean(it) }

        setOf(
            HybridAquaticEntityTypes.YETI_CRAB.get(),
            HybridAquaticEntityTypes.SPIDER_CRAB.get(),
            HybridAquaticEntityTypes.GIANT_ISOPOD.get()
        ).forEach { registerCrustaceanUnderground(it) }

        setOf(
            HybridAquaticEntityTypes.KARKINOS.get(),
        ).forEach { registerMiniboss(it) }
    }

    private fun <T : WaterAnimal> registerFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity.Companion::canSpawn)
    }

    private fun <T : WaterAnimal> registerFishUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity.Companion::canUndergroundSpawn)
    }

    private fun <T : WaterAnimal> registerCephalopod(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCephalopodEntity.Companion::canSpawn)
    }

    private fun <T : WaterAnimal> registerCephalopodUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCephalopodEntity.Companion::canUndergroundSpawn)
    }

    private fun <T : WaterAnimal> registerShark(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity.Companion::canSpawn)
    }

    private fun <T : WaterAnimal> registerSharkUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity.Companion::canUndergroundSpawn)
    }

    private fun <T : WaterAnimal> registerJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity.Companion::canSpawn)
    }

    private fun <T : WaterAnimal> registerJellyUnderground(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity.Companion::canUndergroundSpawn)
    }

    private fun <T : WaterAnimal> registerCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrustaceanEntity.Companion::canSpawn)
    }

    private fun <T : WaterAnimal> registerCrustaceanUnderground(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrustaceanEntity.Companion::canUndergroundSpawn)
    }

    private fun <T : WaterAnimal> registerCritter(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCritterEntity.Companion::canSpawn)
    }

    private fun <T : Monster> registerMiniboss(entityType: EntityType<T>) {
        registerMiniboss(entityType, HybridAquaticMinibossEntity.Companion::canSpawn)
    }

    private fun <T : WaterAnimal> registerWaterCreature(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>
    ) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : Monster> registerMiniboss(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>
    ) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : WaterAnimal> registerLandWaterCreature(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>
    ) {
        register(
            entityType,
            SpawnPlacements.Type.NO_RESTRICTIONS,
            predicate
        )
    }

    private fun <T : Mob> register(
        entityType: EntityType<T>,
        location: SpawnPlacements.Type,
        predicate: SpawnPlacements.SpawnPredicate<T>
    ) {
        SpawnPlacements.register(entityType, location, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}