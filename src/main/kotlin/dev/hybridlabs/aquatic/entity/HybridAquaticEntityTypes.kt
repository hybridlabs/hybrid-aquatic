package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.critter.*
import dev.hybridlabs.aquatic.entity.fish.*
import dev.hybridlabs.aquatic.entity.jellyfish.*
import dev.hybridlabs.aquatic.entity.shark.*
import dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroup
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.*
import net.minecraft.entity.EntityType.EntityFactory
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticEntityTypes {
    val ANGLERFISH = registerFishUnderground(
        "anglerfish",
        ::AnglerfishEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        AnglerfishEntity.createMobAttributes()
    )

    val BARRELEYE = registerFishUnderground(
        "barreleye",
        ::BarreleyeEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        BarreleyeEntity.createMobAttributes()
    )

    val BETTA = registerFish(
        "betta",
        ::BettaEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        BettaEntity.createMobAttributes()
    )

    val BLUE_SPOTTED_STINGRAY = registerFish(
        "blue_spotted_stingray",
        ::BlueSpottedStingrayEntity,
        EntityDimensions.fixed(0.75f, 0.2f),
        BlueSpottedStingrayEntity.createMobAttributes()
    )

    val BLUE_TANG = registerFish(
        "blue_tang",
        ::BlueTangEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        BlueTangEntity.createMobAttributes()
    )

    val CLOWNFISH = registerFish(
        "clownfish",
        ::ClownfishEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        ClownfishEntity.createClownfishAttributes()
    )

    val COWFISH = registerFish(
        "cowfish",
        ::CowfishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        CowfishEntity.createMobAttributes()
    )

    val CUTTLEFISH = registerFish(
        "cuttlefish",
        ::CuttlefishEntity,
        EntityDimensions.fixed(0.5f, 0.35f),
        CuttlefishEntity.createMobAttributes()
    )

    val DISCUS = registerFish(
        "discus",
        ::DiscusEntity,
        EntityDimensions.fixed(0.35f, 0.45f),
        DiscusEntity.createMobAttributes()
    )

    val DRAGONFISH = registerFishUnderground(
        "dragonfish",
        ::DragonfishEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        DragonfishEntity.createMobAttributes()
    )

    val FIREFLY_SQUID = registerFish(
        "firefly_squid",
        ::FireflySquidEntity,
        EntityDimensions.fixed(0.35f, 0.3f),
        FireflySquidEntity.createMobAttributes()
    )

    val FLASHLIGHT_FISH = registerFish(
        "flashlight_fish",
        ::FlashlightFishEntity,
        EntityDimensions.fixed(0.4f, 0.25f),
        FlashlightFishEntity.createMobAttributes()
    )

    val GLOWING_SUCKER_OCTOPUS = registerFish(
        "glowing_sucker_octopus",
        ::GlowingSuckerOctopusEntity,
        EntityDimensions.fixed(0.5f, 0.6f),
        GlowingSuckerOctopusEntity.createMobAttributes()
    )

    val GOURAMI = registerFish(
        "gourami",
        ::GouramiEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GouramiEntity.createMobAttributes()
    )

    val LIONFISH = registerFish(
        "lionfish",
        ::LionfishEntity,
        EntityDimensions.fixed(0.4f, 0.3f),
        LionfishEntity.createMobAttributes()
    )

    val MAHIMAHI = registerFish(
        "mahimahi",
        ::MahiMahiEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        MahiMahiEntity.createMobAttributes()
    )

    val MORAY_EEL = registerFish(
        "moray_eel",
        ::MorayEelEntity,
        EntityDimensions.fixed(0.8f, 0.5f),
        MorayEelEntity.createMobAttributes()
    )

    val NAUTILUS = registerFish(
        "nautilus",
        ::NautilusEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        NautilusEntity.createMobAttributes()
    )

    val NEEDLEFISH = registerFish(
        "needlefish",
        ::NeedlefishEntity,
        EntityDimensions.fixed(0.4f, 0.25f),
        NeedlefishEntity.createMobAttributes()
    )

    val OARFISH = registerFish(
        "oarfish",
        ::OarfishEntity,
        EntityDimensions.fixed(1.5f, 0.65f),
        OarfishEntity.createMobAttributes()
    )

    val OSCAR = registerFish(
        "oscar",
        ::OscarEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        OscarEntity.createMobAttributes()
    )

    val OPAH = registerFish(
    "opah",
    ::OpahEntity,
    EntityDimensions.fixed(0.8f, 1.0f),
    OpahEntity.createMobAttributes()
    )

    val PIRANHA = registerFish(
        "piranha",
        ::PiranhaEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        PiranhaEntity.createMobAttributes()
    )

    val RATFISH = registerFish(
        "ratfish",
        ::RatfishEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        RatfishEntity.createMobAttributes()
    )

    val ROCKFISH = registerFish(
        "rockfish",
        ::RockfishEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        RockfishEntity.createMobAttributes()
    )

    val SEA_ANGEL = registerFishUnderground(
        "sea_angel",
        ::SeaAngelEntity,
        EntityDimensions.fixed(0.3f, 0.2f),
        SeaAngelEntity.createMobAttributes()
    )

    val SEAHORSE = registerFish(
        "seahorse",
        ::SeahorseEntity,
        EntityDimensions.fixed(0.5f, 0.7f),
        SeahorseEntity.createMobAttributes()
    )

    val STONEFISH = registerFish(
        "stonefish",
        ::StonefishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        StonefishEntity.createMobAttributes()
    )

    val SUNFISH = registerFish(
        "sunfish",
        ::SunfishEntity,
        EntityDimensions.fixed(1.25f, 2f),
        SunfishEntity.createMobAttributes()
    )

    val TETRA = registerFish(
        "tetra",
        ::TetraEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        TetraEntity.createMobAttributes()
    )

    val TIGER_BARB = registerFish(
        "tiger_barb",
        ::TigerBarbEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        TigerBarbEntity.createMobAttributes()
    )

    val TOADFISH = registerFish(
        "toadfish",
        ::ToadfishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        ToadfishEntity.createMobAttributes()
    )

    val TRIGGERFISH = registerFish(
        "triggerfish",
        ::TriggerfishEntity,
        EntityDimensions.fixed(0.4f, 0.5f),
        TriggerfishEntity.createMobAttributes()
    )

    val UNICORN_FISH = registerFish(
        "unicorn_fish",
        ::UnicornFishEntity,
        EntityDimensions.fixed(0.35f, 0.4f),
        UnicornFishEntity.createMobAttributes()
    )

    val VAMPIRE_SQUID = registerFishUnderground(
        "vampire_squid",
        ::VampireSquidEntity,
        EntityDimensions.fixed(0.6f, 0.4f),
        VampireSquidEntity.createMobAttributes()
    )

    val YELLOWFIN_TUNA = registerFish(
        "yellowfin_tuna",
        ::YellowfinTunaEntity,
        EntityDimensions.fixed(1.0f, 0.7f),
        YellowfinTunaEntity.createMobAttributes()
    )

    val ZEBRA_DANIO = registerFish(
        "zebra_danio",
        ::ZebraDanioEntity,
        EntityDimensions.fixed(0.3f, 0.25f),
        ZebraDanioEntity.createMobAttributes()
    )

    //crustaceans

    val COCONUT_CRAB = registerCritter(
        "coconut_crab",
        ::CoconutCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CoconutCrabEntity.createMobAttributes()
    )

    val CRAB = registerCritter(
        "crab",
        ::CrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CrabEntity.createMobAttributes()
    )

    val CRAYFISH = registerCritter(
        "crayfish",
        ::CrayfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CrayfishEntity.createMobAttributes()
    )

    val FIDDLER_CRAB = registerCritter(
        "fiddler_crab",
        ::FiddlerCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        FiddlerCrabEntity.createMobAttributes()
    )

    val FLOWER_CRAB = registerCritter(
        "flower_crab",
        ::FlowerCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        FlowerCrabEntity.createMobAttributes()
    )

    val GHOST_CRAB = registerCritter(
        "ghost_crab",
        ::GhostCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GhostCrabEntity.createMobAttributes()
    )

    val GIANT_ISOPOD = registerCritter(
        "giant_isopod",
        ::GiantIsopodEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GiantIsopodEntity.createMobAttributes()
    )

    val HERMIT_CRAB = registerCritter(
        "hermit_crab",
        ::HermitCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        HermitCrabEntity.createMobAttributes()
    )

    val HORSESHOE_CRAB = registerCritter(
        "horseshoe_crab",
        ::HorseshoeCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        HorseshoeCrabEntity.createMobAttributes()
    )

    val LIGHTFOOT_CRAB = registerCritter(
        "lightfoot_crab",
        ::LightfootCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        LightfootCrabEntity.createMobAttributes()
    )

    val LOBSTER = registerCritter(
        "lobster",
        ::LobsterEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        LobsterEntity.createMobAttributes()
    )

    val MANTIS_SHRIMP = registerCritter(
        "mantis_shrimp",
        ::MantisShrimpEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        MantisShrimpEntity.createMobAttributes()
    )

    val SHRIMP = registerCritter(
        "shrimp",
        ::ShrimpEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        ShrimpEntity.createMobAttributes()
    )

    val SPIDER_CRAB = registerCritter(
        "spider_crab",
        ::SpiderCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        SpiderCrabEntity.createMobAttributes()
    )

    val VAMPIRE_CRAB = registerCritter(
        "vampire_crab",
        ::VampireCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        VampireCrabEntity.createMobAttributes()
    )

    val YETI_CRAB = registerCritter(
        "yeti_crab",
        ::YetiCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        YetiCrabEntity.createMobAttributes()
    )

    val KARKINOS = registerCritter(
        "karkinos",
        ::KarkinosEntity,
        EntityDimensions.fixed(2.25f, 1.25f),
        KarkinosEntity.createMobAttributes()
    )


    //critters

    val GIANT_CLAM = registerCritter(
        "giant_clam",
        ::GiantClamEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GiantClamEntity.createMobAttributes()
    )

    val NUDIBRANCH = registerCritter(
        "nudibranch",
        ::NudibranchEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        NudibranchEntity.createMobAttributes()
    )

    val SEA_CUCUMBER = registerCritter(
        "sea_cucumber",
        ::SeaCucumberEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        SeaCucumberEntity.createMobAttributes()
    )

    val SEA_URCHIN = registerCritter(
        "sea_urchin",
        ::SeaUrchinEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        SeaUrchinEntity.createMobAttributes()
    )

    val STARFISH = registerCritter(
        "starfish",
        ::StarfishEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        StarfishEntity.createMobAttributes()
    )

    //jellyfish

    val ATOLLA_JELLYFISH = registerJelly(
        "atolla_jellyfish",
        ::AtollaJellyfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        AtollaJellyfishEntity.createMobAttributes()
    )

    val BARREL_JELLYFISH = registerJelly(
        "barrel_jellyfish",
        ::BarrelJellyfishEntity,
        EntityDimensions.fixed(0.75f, 1.25f),
        BarrelJellyfishEntity.createMobAttributes()
    )

    val BLUE_JELLYFISH = registerJelly(
        "blue_jellyfish",
        ::BlueJellyfishEntity,
        EntityDimensions.fixed(0.5f, 1.0f),
        BlueJellyfishEntity.createMobAttributes()
    )

    val CAULIFLOWER_JELLYFISH = registerJelly(
        "cauliflower_jellyfish",
        ::CauliflowerJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CauliflowerJellyfishEntity.createMobAttributes()
    )

    val COMPASS_JELLYFISH = registerJelly(
        "compass_jellyfish",
        ::CompassJellyfishEntity,
        EntityDimensions.fixed(0.5f, 1.0f),
        CompassJellyfishEntity.createMobAttributes()
    )

    val FRIED_EGG_JELLYFISH = registerJelly(
        "fried_egg_jellyfish",
        ::FriedEggJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        FriedEggJellyfishEntity.createMobAttributes()
    )

    val LIONS_MANE_JELLYFISH = registerJelly(
        "lions_mane_jellyfish",
        ::LionsManeJellyfishEntity,
        EntityDimensions.fixed(2.0f, 2.5f),
        LionsManeJellyfishEntity.createMobAttributes()
    )

    val MAUVE_STINGER = registerJelly(
        "mauve_stinger",
        ::MauveStingerEntity,
        EntityDimensions.fixed(0.25f, 0.35f),
        MauveStingerEntity.createMobAttributes()
    )

    val MOON_JELLYFISH = registerJelly(
        "moon_jellyfish",
        ::MoonJellyfishEntity,
        EntityDimensions.fixed(0.35f, 0.35f),
        MoonJellyfishEntity.createMobAttributes()
    )

    val NOMURA_JELLYFISH = registerJelly(
        "nomura_jellyfish",
        ::NomuraJellyfishEntity,
        EntityDimensions.fixed(1.25f, 2.0f),
        NomuraJellyfishEntity.createMobAttributes()
    )

    val SEA_NETTLE = registerJelly(
        "sea_nettle",
        ::SeaNettleEntity,
        EntityDimensions.fixed(0.5f, 2.75f),
        SeaNettleEntity.createMobAttributes()
    )

    //sharks

    val BASKING_SHARK = registerShark(
        "basking_shark",
        ::BaskingSharkEntity,
        EntityDimensions.fixed(2.5f, 1.0f),
        BaskingSharkEntity.createMobAttributes()
    )

    val BULL_SHARK = registerShark(
        "bull_shark",
        ::BullSharkEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        BullSharkEntity.createMobAttributes()
    )

    val FRILLED_SHARK = registerFishUnderground(
        "frilled_shark",
        ::FrilledSharkEntity,
        EntityDimensions.fixed(1.5f, 1.0f),
        FrilledSharkEntity.createMobAttributes()
    )

    val GREAT_WHITE_SHARK = registerShark(
        "great_white_shark",
        ::GreatWhiteSharkEntity,
        EntityDimensions.fixed(2.0f, 1.5f),
        GreatWhiteSharkEntity.createMobAttributes()
    )

    val HAMMERHEAD_SHARK = registerShark(
        "hammerhead_shark",
        ::HammerheadSharkEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        HammerheadSharkEntity.createMobAttributes()
    )

    val THRESHER_SHARK = registerShark(
        "thresher_shark",
        ::ThresherSharkEntity,
        EntityDimensions.fixed(2.0f, 0.5f),
        ThresherSharkEntity.createMobAttributes()
    )

    val TIGER_SHARK = registerShark(
        "tiger_shark",
        ::TigerSharkEntity,
        EntityDimensions.fixed(2.0f, 1.5f),
        TigerSharkEntity.createMobAttributes()
    )

    val WHALE_SHARK = registerShark(
        "whale_shark",
        ::WhaleSharkEntity,
        EntityDimensions.fixed(2.5f, 1.0f),
        WhaleSharkEntity.createMobAttributes()
    )

    private fun <T : LivingEntity> registerShark(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.SHARK)
    }

    private fun <T : LivingEntity> registerCritter(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.CRITTER)
    }

    private fun <T : LivingEntity> registerFish(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.FISH)
    }

    private fun <T : LivingEntity> registerFishUnderground(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.FISH_UNDERGROUND)
    }

    private fun <T : LivingEntity> registerJelly(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.JELLY)
    }

    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    private fun <T : LivingEntity> registerCustomSpawnGroup(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder,
        hybridAquaticSpawnGroup: HybridAquaticSpawnGroup
    ): EntityType<T> {
        return registerLiving(id, entityFactory, dimensions, attributeContainer, hybridAquaticSpawnGroup.spawnGroup)
    }

    /**
     * Registers a living entity to the entity type registry.
     */
    private fun <T : LivingEntity> registerLiving(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder,
        spawnGroup: SpawnGroup
    ): EntityType<T> {
        val entityType = FabricEntityTypeBuilder.create(spawnGroup, entityFactory).dimensions(dimensions).build()
        FabricDefaultAttributeRegistry.register(entityType, attributeContainer)
        return register(id, entityType)
    }

    /**
     * Registers an entity type to the entity type registry.
     */
    private fun <T : Entity> register(id: String, entity: EntityType<T>): EntityType<T> {
        return Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
    }
}
