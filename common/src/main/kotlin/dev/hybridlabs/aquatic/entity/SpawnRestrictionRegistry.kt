package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.entity.cephalopod.FireflySquidEntity
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticOctopusEntity
import dev.hybridlabs.aquatic.entity.cephalopod.NautilusEntity
import dev.hybridlabs.aquatic.entity.cephalopod.UmbrellaOctopusEntity
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.crustacean.CoconutCrabEntity
import dev.hybridlabs.aquatic.entity.crustacean.GhostCrabEntity
import dev.hybridlabs.aquatic.entity.crustacean.HorseshoeCrabEntity
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import dev.hybridlabs.aquatic.entity.crustacean.SpiderCrabEntity
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import dev.hybridlabs.aquatic.entity.fish.DragonfishEntity
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import dev.hybridlabs.aquatic.entity.fish.FlyingFishEntity
import dev.hybridlabs.aquatic.entity.fish.GoldfishEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.fish.OarfishEntity
import dev.hybridlabs.aquatic.entity.fish.ParrotfishEntity
import dev.hybridlabs.aquatic.entity.fish.PupfishEntity
import dev.hybridlabs.aquatic.entity.fish.SeahorseEntity
import dev.hybridlabs.aquatic.entity.fish.SquirrelfishEntity
import dev.hybridlabs.aquatic.entity.fish.SunfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticMammalEntity
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinionEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.animal.Animal
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
            HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
            HybridAquaticEntityTypes.DAMSELFISH.get(),
            HybridAquaticEntityTypes.TIGER_BARB.get(),
            HybridAquaticEntityTypes.PIRANHA.get(),
            HybridAquaticEntityTypes.SHINER.get(),
            HybridAquaticEntityTypes.OSCAR.get(),
            HybridAquaticEntityTypes.GOURAMI.get(),
            HybridAquaticEntityTypes.PLECO.get(),
            HybridAquaticEntityTypes.DANIO.get(),
            HybridAquaticEntityTypes.DISCUS.get(),
            HybridAquaticEntityTypes.BETTA.get(),
            HybridAquaticEntityTypes.TETRA.get(),
            HybridAquaticEntityTypes.GOLDEN_DORADO.get(),
        ).forEach { registerShallowFish(it) }

        // fish
        setOf(
            HybridAquaticEntityTypes.TUNA.get(),
            HybridAquaticEntityTypes.MAHI.get(),
            HybridAquaticEntityTypes.BARRACUDA.get(),
            HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticEntityTypes.BOXFISH.get(),
            HybridAquaticEntityTypes.SEADRAGON.get(),
            HybridAquaticEntityTypes.LIONFISH.get(),
            HybridAquaticEntityTypes.BLOWFISH.get(),
            HybridAquaticEntityTypes.STONEFISH.get(),
            HybridAquaticEntityTypes.ROCKFISH.get(),
            HybridAquaticEntityTypes.SEA_BASS.get(),
            HybridAquaticEntityTypes.TRIGGERFISH.get(),
            HybridAquaticEntityTypes.WRASSE.get(),
            HybridAquaticEntityTypes.NEEDLEFISH.get(),
            HybridAquaticEntityTypes.MACKEREL.get(),
            HybridAquaticEntityTypes.HERRING.get(),
            HybridAquaticEntityTypes.STINGRAY.get(),
            HybridAquaticEntityTypes.MANTA_RAY.get(),
            HybridAquaticEntityTypes.PEARLFISH.get(),
        ).forEach { registerFish(it) }

        // night fish
        setOf(
            HybridAquaticEntityTypes.MORAY_EEL.get(),
            HybridAquaticEntityTypes.OPAH.get(),
        ).forEach { registerNightFish(it) }

        registerWaterCreature(HybridAquaticEntityTypes.CARP.get(), CarpEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.GOLDFISH.get(), GoldfishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.CLOWNFISH.get(), ClownfishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.PARROTFISH.get(), ParrotfishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.SEAHORSE.get(), SeahorseEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.PUPFISH.get(), PupfishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.FLYING_FISH.get(), FlyingFishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.SUNFISH.get(), SunfishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.SQUIRRELFISH.get(), SquirrelfishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(), FlashlightFishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.DRAGONFISH.get(), DragonfishEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.OARFISH.get(), OarfishEntity::canSpawn)

        // deep fish
        setOf(
            HybridAquaticEntityTypes.ANGLERFISH.get(),
            HybridAquaticEntityTypes.BARRELEYE.get(),
            HybridAquaticEntityTypes.COELACANTH.get(),
            HybridAquaticEntityTypes.RATFISH.get(),
            HybridAquaticEntityTypes.JOHN_DORY.get(),
            HybridAquaticEntityTypes.SNAILFISH.get(),
            HybridAquaticEntityTypes.SEA_ANGEL.get(),
        ).forEach { registerDeepFish(it) }

        // cephalopods
        setOf(
            HybridAquaticEntityTypes.ARROW_SQUID.get(),
            HybridAquaticEntityTypes.CUTTLEFISH.get(),
        ).forEach { registerCephalopod(it) }

        // deep cephalopods
        setOf(
            HybridAquaticEntityTypes.VAMPIRE_SQUID.get(),
        ).forEach { registerDeepCephalopod(it) }

        // octopuses
        setOf(
            HybridAquaticEntityTypes.OCTOPUS.get(),
        ).forEach { registerOctopus(it) }

        registerWaterCreature(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), UmbrellaOctopusEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.NAUTILUS.get(), NautilusEntity::canSpawn)
        registerWaterCreature(HybridAquaticEntityTypes.FIREFLY_SQUID.get(), FireflySquidEntity::canSpawn)

        // jellies
        setOf(
            HybridAquaticEntityTypes.MOON_JELLYFISH.get(),
            HybridAquaticEntityTypes.SEA_NETTLE.get(),
            HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH.get(),
            HybridAquaticEntityTypes.BLUE_JELLYFISH.get(),
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get(),
            HybridAquaticEntityTypes.NOMURA_JELLYFISH.get(),
            HybridAquaticEntityTypes.BARREL_JELLYFISH.get(),
            HybridAquaticEntityTypes.BOX_JELLYFISH.get(),
        ).forEach { registerJelly(it) }

        setOf(
            HybridAquaticEntityTypes.CROWN_JELLYFISH.get(),
            HybridAquaticEntityTypes.BIG_RED_JELLYFISH.get(),
            HybridAquaticEntityTypes.COSMIC_JELLYFISH.get(),
            HybridAquaticEntityTypes.FIREWORK_JELLYFISH.get(),
            HybridAquaticEntityTypes.MAUVE_STINGER.get(),
        ).forEach { registerDeepJelly(it) }

        // sharks
        setOf(
            HybridAquaticEntityTypes.WHALE_SHARK.get(),
            HybridAquaticEntityTypes.BASKING_SHARK.get(),
        ).forEach { registerShallowShark(it) }

        setOf(
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(),
            HybridAquaticEntityTypes.TIGER_SHARK.get(),
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get(),
            HybridAquaticEntityTypes.HOUND_SHARK.get(),
            HybridAquaticEntityTypes.THRESHER_SHARK.get(),
            HybridAquaticEntityTypes.BULL_SHARK.get(),
        ).forEach { registerShark(it) }

        setOf(
            HybridAquaticEntityTypes.FRILLED_SHARK.get(),
            HybridAquaticEntityTypes.LANTERN_SHARK.get(),
        ).forEach { registerDeepShark(it) }

        setOf(
            HybridAquaticEntityTypes.OTTER.get(),
        ).forEach { registerMammal(it) }

        // critters
        setOf(
            HybridAquaticEntityTypes.STARFISH.get(),
            HybridAquaticEntityTypes.SEA_SLUG.get(),
            HybridAquaticEntityTypes.SEA_CUCUMBER.get(),
            HybridAquaticEntityTypes.SEA_URCHIN.get(),
        ).forEach { registerCritter(it) }

        // crustaceans
        setOf(
            HybridAquaticEntityTypes.FIDDLER_CRAB.get(),
            HybridAquaticEntityTypes.HERMIT_CRAB.get(),
            HybridAquaticEntityTypes.VAMPIRE_CRAB.get(),
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get(),
        ).forEach { registerTerrestrialCrustacean(it) }

        setOf(
            HybridAquaticEntityTypes.DUNGENESS_CRAB.get(),
            HybridAquaticEntityTypes.FLOWER_CRAB.get(),
            HybridAquaticEntityTypes.DECORATOR_CRAB.get(),
            HybridAquaticEntityTypes.SHRIMP.get(),
            HybridAquaticEntityTypes.CRAYFISH.get(),
            HybridAquaticEntityTypes.LOBSTER.get(),
        ).forEach { registerAquaticCrustacean(it) }

        registerLandWaterCreature(HybridAquaticEntityTypes.HORSESHOE_CRAB.get(), HorseshoeCrabEntity::canSpawn)
        registerLandWaterCreature(HybridAquaticEntityTypes.GHOST_CRAB.get(), GhostCrabEntity::canSpawn)
        registerLandWaterCreature(HybridAquaticEntityTypes.COCONUT_CRAB.get(), CoconutCrabEntity::canSpawn)
        registerLandWaterCreature(HybridAquaticEntityTypes.SPIDER_CRAB.get(), SpiderCrabEntity::canSpawn)

        setOf(
            HybridAquaticEntityTypes.YETI_CRAB.get(),
            HybridAquaticEntityTypes.GIANT_ISOPOD.get()
        ).forEach { registerDeepCrustacean(it) }

        setOf(
            HybridAquaticEntityTypes.KARKINOS.get(),
        ).forEach { registerMiniboss(it) }

        setOf(
            HybridAquaticEntityTypes.KARCINOGEN.get(),
            HybridAquaticEntityTypes.KARCINOMA.get(),
        ).forEach { registerMinion(it) }
    }

    private fun <T : WaterAnimal> registerShallowFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canShallowSpawn)
    }

    private fun <T : WaterAnimal> registerFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerNightFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canNightSpawn)
    }

    private fun <T : WaterAnimal> registerDeepFish(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticFishEntity::canDeepSpawn)
    }

    private fun <T : WaterAnimal> registerCephalopod(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCephalopodEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerOctopus(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticOctopusEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerDeepCephalopod(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCephalopodEntity::canDeepSpawn)
    }

    private fun <T : WaterAnimal> registerShallowShark(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity::canShallowSpawn)
    }

    private fun <T : WaterAnimal> registerShark(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerDeepShark(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticSharkEntity::canDeepSpawn)
    }

    private fun <T : HybridAquaticMammalEntity> registerMammal(entityType: EntityType<T>) {
        registerMammalEntity(entityType, HybridAquaticMammalEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity::canSpawn)
    }

    private fun <T : WaterAnimal> registerDeepJelly(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticJellyfishEntity::canDeepSpawn)
    }

    private fun <T : WaterAnimal> registerTerrestrialCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrustaceanEntity::canSurfaceSpawn)
    }

    private fun <T : WaterAnimal> registerAquaticCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrustaceanEntity::canWaterSpawn)
    }

    private fun <T : WaterAnimal> registerDeepCrustacean(entityType: EntityType<T>) {
        registerLandWaterCreature(entityType, HybridAquaticCrustaceanEntity::canDeepSpawn)
    }

    private fun <T : WaterAnimal> registerCritter(entityType: EntityType<T>) {
        registerWaterCreature(entityType, HybridAquaticCritterEntity::canSpawn)
    }

    private fun <T : Monster> registerMiniboss(entityType: EntityType<T>) {
        registerMiniboss(entityType, HybridAquaticMinibossEntity::canSpawn)
    }

    private fun <T : Monster> registerMinion(entityType: EntityType<T>) {
        registerMinion(entityType, HybridAquaticMinionEntity::canSpawn)
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

    private fun <T : Animal> registerMammalEntity(entityType: EntityType<T>, predicate: SpawnPlacements.SpawnPredicate<T>) {
        register(
            entityType,
            SpawnPlacements.Type.NO_RESTRICTIONS,
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
