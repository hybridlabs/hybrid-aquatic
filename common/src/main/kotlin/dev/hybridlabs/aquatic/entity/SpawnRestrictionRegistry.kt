package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.entity.cephalopod.*
import dev.hybridlabs.aquatic.entity.critter.HACritterEntity
import dev.hybridlabs.aquatic.entity.crustacean.*
import dev.hybridlabs.aquatic.entity.fish.*
import dev.hybridlabs.aquatic.entity.jellyfish.HAJellyfishEntity
import dev.hybridlabs.aquatic.entity.mammal.HADolphinEntity
import dev.hybridlabs.aquatic.entity.mammal.HAMammalEntity
import dev.hybridlabs.aquatic.entity.mammal.HASirenianEntity
import dev.hybridlabs.aquatic.entity.miniboss.HAMinibossEntity
import dev.hybridlabs.aquatic.entity.miniboss.HAMinionEntity
import dev.hybridlabs.aquatic.entity.shark.HASharkEntity
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
        // shallow fish
        setOf(
            HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
            HAEntityTypes.DAMSELFISH.get(),
            HAEntityTypes.TIGER_BARB.get(),
            HAEntityTypes.PIRANHA.get(),
            HAEntityTypes.SHINER.get(),
            HAEntityTypes.TROUT.get(),
            HAEntityTypes.SUNFISH.get(),
            HAEntityTypes.OSCAR.get(),
            HAEntityTypes.GOURAMI.get(),
            HAEntityTypes.PLECO.get(),
            HAEntityTypes.DANIO.get(),
            HAEntityTypes.DISCUS.get(),
            HAEntityTypes.CORYDORA.get(),
            HAEntityTypes.BETTA.get(),
            HAEntityTypes.TETRA.get(),
            HAEntityTypes.GOLDEN_DORADO.get(),
        ).forEach { registerShallowFish(it) }

        // fish
        setOf(
            HAEntityTypes.TUNA.get(),
            HAEntityTypes.MAHI.get(),
            HAEntityTypes.BARRACUDA.get(),
            HAEntityTypes.SURGEONFISH.get(),
            HAEntityTypes.BOXFISH.get(),
            HAEntityTypes.SEADRAGON.get(),
            HAEntityTypes.LIONFISH.get(),
            HAEntityTypes.BLOWFISH.get(),
            HAEntityTypes.STONEFISH.get(),
            HAEntityTypes.ROCKFISH.get(),
            HAEntityTypes.SEA_BASS.get(),
            HAEntityTypes.TRIGGERFISH.get(),
            HAEntityTypes.TREVALLY.get(),
            HAEntityTypes.WRASSE.get(),
            HAEntityTypes.NEEDLEFISH.get(),
            HAEntityTypes.MACKEREL.get(),
            HAEntityTypes.HERRING.get(),
            HAEntityTypes.STINGRAY.get(),
            HAEntityTypes.MANTA_RAY.get(),
            HAEntityTypes.PEARLFISH.get(),
        ).forEach { registerFish(it) }

        // night fish
        setOf(
            HAEntityTypes.MORAY_EEL.get(),
            HAEntityTypes.OPAH.get(),
        ).forEach { registerNightFish(it) }

        registerFish(HAEntityTypes.GARDEN_EEL.get(), GardenEelEntity::canSpawn)
        registerFish(HAEntityTypes.CARP.get(), CarpEntity::canSpawn)
        registerFish(HAEntityTypes.GOLDFISH.get(), GoldfishEntity::canSpawn)
        registerFish(HAEntityTypes.CLOWNFISH.get(), ClownfishEntity::canSpawn)
        registerFish(HAEntityTypes.PARROTFISH.get(), ParrotfishEntity::canSpawn)
        registerFish(HAEntityTypes.SEAHORSE.get(), SeahorseEntity::canSpawn)
        registerFish(HAEntityTypes.PUPFISH.get(), PupfishEntity::canSpawn)
        registerFish(HAEntityTypes.FLYING_FISH.get(), FlyingFishEntity::canSpawn)
        registerFish(HAEntityTypes.OCEAN_SUNFISH.get(), OceanSunfishEntity::canSpawn)
        registerFish(HAEntityTypes.SQUIRRELFISH.get(), SquirrelfishEntity::canSpawn)
        registerFish(HAEntityTypes.FLASHLIGHT_FISH.get(), FlashlightFishEntity::canSpawn)
        registerFish(HAEntityTypes.DRAGONFISH.get(), DragonfishEntity::canSpawn)
        registerFish(HAEntityTypes.OARFISH.get(), OarfishEntity::canSpawn)
        registerFish(HAEntityTypes.TRIPOD_FISH.get(), TripodFishEntity::canSpawn)

        // deep fish
        setOf(
            HAEntityTypes.ANGLERFISH.get(),
            HAEntityTypes.VIPERFISH.get(),
            HAEntityTypes.FANGTOOTH.get(),
            HAEntityTypes.HATCHETFISH.get(),
            HAEntityTypes.BARRELEYE.get(),
            HAEntityTypes.COELACANTH.get(),
            HAEntityTypes.SLICKHEAD.get(),
            HAEntityTypes.RATFISH.get(),
            HAEntityTypes.JOHN_DORY.get(),
            HAEntityTypes.SNAILFISH.get(),
            HAEntityTypes.SEA_ANGEL.get(),
        ).forEach { registerDeepFish(it) }

        // cephalopods
        setOf(
            HAEntityTypes.ARROW_SQUID.get(),
            HAEntityTypes.CUTTLEFISH.get(),
        ).forEach { registerCephalopod(it) }

        // deep cephalopods
        setOf(
            HAEntityTypes.VAMPIRE_SQUID.get()
        ).forEach { registerDeepCephalopod(it) }

        // octopuses
        setOf(
            HAEntityTypes.OCTOPUS.get(),
        ).forEach { registerOctopus(it) }

        registerWaterCreature(HAEntityTypes.COLOSSAL_SQUID.get(), ColossalSquidEntity::canSpawn)
        registerWaterCreature(HAEntityTypes.GIANT_SQUID.get(), GiantSquidEntity::canSpawn)
        registerWaterCreature(HAEntityTypes.UMBRELLA_OCTOPUS.get(), UmbrellaOctopusEntity::canSpawn)
        registerWaterCreature(HAEntityTypes.NAUTILUS.get(), NautilusEntity::canSpawn)
        registerWaterCreature(HAEntityTypes.FIREFLY_SQUID.get(), FireflySquidEntity::canSpawn)

        // jellies
        setOf(
            HAEntityTypes.MOON_JELLYFISH.get(),
            HAEntityTypes.SEA_NETTLE.get(),
            HAEntityTypes.CEPHEIDAE_JELLYFISH.get(),
            HAEntityTypes.BLUE_JELLYFISH.get(),
            HAEntityTypes.LIONS_MANE_JELLYFISH.get(),
            HAEntityTypes.NOMURA_JELLYFISH.get(),
            HAEntityTypes.BARREL_JELLYFISH.get(),
            HAEntityTypes.BOX_JELLYFISH.get(),
        ).forEach { registerJelly(it) }

        setOf(
            HAEntityTypes.CROWN_JELLYFISH.get(),
            HAEntityTypes.BIG_RED_JELLYFISH.get(),
            HAEntityTypes.COSMIC_JELLYFISH.get(),
            HAEntityTypes.FIREWORK_JELLYFISH.get(),
            HAEntityTypes.MAUVE_STINGER.get(),
        ).forEach { registerDeepJelly(it) }

        // sharks
        setOf(
            HAEntityTypes.WHALE_SHARK.get(),
            HAEntityTypes.BASKING_SHARK.get(),
        ).forEach { registerShallowShark(it) }

        setOf(
            HAEntityTypes.GREAT_WHITE_SHARK.get(),
            HAEntityTypes.SAND_TIGER_SHARK.get(),
            HAEntityTypes.HAMMERHEAD_SHARK.get(),
            HAEntityTypes.HOUND_SHARK.get(),
            HAEntityTypes.THRESHER_SHARK.get(),
            HAEntityTypes.BULL_SHARK.get(),
        ).forEach { registerShark(it) }

        setOf(
            HAEntityTypes.FRILLED_SHARK.get(),
            HAEntityTypes.SIXGILL_SHARK.get(),
            HAEntityTypes.SLEEPER_SHARK.get(),
            HAEntityTypes.LANTERN_SHARK.get(),
        ).forEach { registerDeepShark(it) }

        setOf(
            HAEntityTypes.OTTER.get(),
        ).forEach { registerMammal(it) }

        setOf(
            HAEntityTypes.DUGONG.get(),
            HAEntityTypes.MANATEE.get(),
        ).forEach { registerSirenian(it) }

        setOf(
            HAEntityTypes.ORCA.get(),
        ).forEach { registerDolphin(it) }

        // critters
        setOf(
            HAEntityTypes.STARFISH.get(),
            HAEntityTypes.SEA_SLUG.get(),
            HAEntityTypes.SCALYFOOT_SNAIL.get(),
            HAEntityTypes.SEA_CUCUMBER.get(),
            HAEntityTypes.SEA_URCHIN.get(),
        ).forEach { registerCritter(it) }

        // crustaceans
        setOf(
            HAEntityTypes.FIDDLER_CRAB.get(),
            HAEntityTypes.HERMIT_CRAB.get(),
            HAEntityTypes.VAMPIRE_CRAB.get(),
            HAEntityTypes.LIGHTFOOT_CRAB.get(),
        ).forEach { registerTerrestrialCrustacean(it) }

        setOf(
            HAEntityTypes.DUNGENESS_CRAB.get(),
            HAEntityTypes.FLOWER_CRAB.get(),
            HAEntityTypes.DECORATOR_CRAB.get(),
            HAEntityTypes.SHRIMP.get(),
            HAEntityTypes.CRAYFISH.get(),
            HAEntityTypes.LOBSTER.get(),
        ).forEach { registerAquaticCrustacean(it) }

        registerLandWaterCreature(HAEntityTypes.HORSESHOE_CRAB.get(), HorseshoeCrabEntity::canSpawn)
        registerLandWaterCreature(HAEntityTypes.GHOST_CRAB.get(), GhostCrabEntity::canSpawn)
        registerLandWaterCreature(HAEntityTypes.COCONUT_CRAB.get(), CoconutCrabEntity::canSpawn)
        registerLandWaterCreature(HAEntityTypes.SPIDER_CRAB.get(), SpiderCrabEntity::canSpawn)

        setOf(
            HAEntityTypes.YETI_CRAB.get(),
            HAEntityTypes.GIANT_ISOPOD.get()
        ).forEach { registerDeepCrustacean(it) }

        setOf(
            HAEntityTypes.KARKINOS.get(),
            HAEntityTypes.SHELL_BEAST.get(),
        ).forEach { registerMiniboss(it) }

        setOf(
            HAEntityTypes.KARCINOGEN.get(),
            HAEntityTypes.KARCINOMA.get(),
        ).forEach { registerMinion(it) }
    }

    private fun <T : HAWaterAnimal> registerShallowFish(entityType: EntityType<T>) {
        registerFish(entityType, HAFishEntity::canShallowSpawn)
    }

    private fun <T : HAWaterAnimal> registerFish(entityType: EntityType<T>) {
        registerFish(entityType, HAFishEntity::canSpawn)
    }

    private fun <T : HAWaterAnimal> registerNightFish(entityType: EntityType<T>) {
        registerFish(entityType, HAFishEntity::canNightSpawn)
    }

    private fun <T : HAWaterAnimal> registerDeepFish(entityType: EntityType<T>) {
        registerFish(entityType, HAFishEntity::canDeepSpawn)
    }

    private fun <T : WaterAnimal> registerCephalopod(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HACephalopodEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerOctopus(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HAOctopusEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerDeepCephalopod(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HACephalopodEntity::canDeepSpawn)
    }

    private fun <T : HAWaterAnimal> registerShallowShark(entityType: EntityType<T>) {
        registerShark(entityType, HASharkEntity::canShallowSpawn)
    }

    private fun <T : HAWaterAnimal> registerShark(entityType: EntityType<T>) {
        registerShark(entityType, HASharkEntity::canSpawn)
    }

    private fun <T : HAWaterAnimal> registerDeepShark(entityType: EntityType<T>) {
        registerShark(entityType, HASharkEntity::canDeepSpawn)
    }

    private fun <T : HAMammalEntity> registerMammal(entityType: EntityType<T>) {
        registerMammalEntity(entityType, HAMammalEntity::canSpawn)
    }

    private fun <T : HASirenianEntity> registerSirenian(entityType: EntityType<T>) {
        registerSirenianEntity(entityType, HASirenianEntity::canSpawn)
    }

    private fun <T : HADolphinEntity> registerDolphin(entityType: EntityType<T>) {
        registerDolphinEntity(entityType, HADolphinEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HAJellyfishEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerDeepJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HAJellyfishEntity::canDeepSpawn)
    }

    private fun <T : WaterAnimal> registerTerrestrialCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HACrustaceanEntity::canSurfaceSpawn)
    }

    private fun <T : WaterAnimal> registerAquaticCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HACrustaceanEntity::canWaterSpawn)
    }

    private fun <T : WaterAnimal> registerDeepCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HACrustaceanEntity::canDeepSpawn)
    }

    private fun <T : WaterAnimal> registerCritter(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HACritterEntity::canSpawn)
    }

    private fun <T : Monster> registerMiniboss(entityType: EntityType<T>) {
        registerMiniboss(entityType, HAMinibossEntity::canSpawn)
    }

    private fun <T : Monster> registerMinion(entityType: EntityType<T>) {
        registerMinion(entityType, HAMinionEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerWaterCreature(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : HAWaterAnimal> registerFish(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : HAWaterAnimal> registerShark(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : Monster> registerMiniboss(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : Monster> registerMinion(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : HAWaterAnimal> registerMammalEntity(entityType: EntityType<T>, predicate: SpawnPlacements.SpawnPredicate<T>) {
        register(
            entityType,
            SpawnPlacements.Type.NO_RESTRICTIONS,
            predicate
        )
    }

    private fun <T : HAWaterAnimal> registerSirenianEntity(entityType: EntityType<T>, predicate: SpawnPlacements.SpawnPredicate<T>) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : HAWaterAnimal> registerDolphinEntity(entityType: EntityType<T>, predicate: SpawnPlacements.SpawnPredicate<T>) {
        register(
            entityType,
            SpawnPlacements.Type.IN_WATER,
            predicate
        )
    }

    private fun <T : WaterAnimal> registerLandWaterCreature(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
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
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        SpawnPlacements.register(entityType, location, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
