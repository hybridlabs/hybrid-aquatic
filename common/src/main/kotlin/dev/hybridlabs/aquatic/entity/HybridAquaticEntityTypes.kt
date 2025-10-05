package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.cephalopod.*
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import dev.hybridlabs.aquatic.entity.crustacean.*
import dev.hybridlabs.aquatic.entity.fish.*
import dev.hybridlabs.aquatic.entity.jellyfish.*
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import dev.hybridlabs.aquatic.entity.miniboss.KarcinogenEntity
import dev.hybridlabs.aquatic.entity.miniboss.KarkinosEntity
import dev.hybridlabs.aquatic.entity.shark.*
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import java.util.concurrent.Callable

@Suppress("SameParameterValue")
object HybridAquaticEntityTypes {

    //#region fish

    val AFRICAN_BUTTERFLYFISH = registerFish(
        "african_butterflyfish",
        ::AfricanButterflyfishEntity,
        EntityDimensions.fixed(0.25f, 0.2f),
        AfricanButterflyfishEntity::createMobAttributes
    )

    val DAMSELFISH = registerFish(
        "damselfish",
        ::DamselfishEntity,
        EntityDimensions.scalable(0.25f, 0.25f),
        DamselfishEntity::createMobAttributes
    )

    val FLYING_FISH = registerFish(
        "flying_fish",
        ::FlyingFishEntity,
        EntityDimensions.fixed(0.25f, 0.2f),
        FlyingFishEntity::createMobAttributes
    )

    val ANGLERFISH = registerFishUnderground(
        "anglerfish",
        ::AnglerfishEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        AnglerfishEntity::createMobAttributes
    )

    val SNAILFISH = registerFishUnderground(
        "snailfish",
        ::SnailfishEntity,
        EntityDimensions.fixed(0.3f, 0.25f),
        SnailfishEntity::createMobAttributes
    )

    val BARRELEYE = registerFishUnderground(
        "barreleye",
        ::BarreleyeEntity,
        EntityDimensions.fixed(0.25f, 0.2f),
        BarreleyeEntity::createMobAttributes
    )

    val BARRACUDA = registerFish(
        "barracuda",
        ::BarracudaEntity,
        EntityDimensions.fixed(1.0f, 0.5f),
        BarracudaEntity::createMobAttributes
    )

    val BETTA = registerFish(
        "betta",
        ::BettaEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        BettaEntity::createMobAttributes
    )

    val PEARLFISH = registerFish(
        "pearlfish",
        ::PearlfishEntity,
        EntityDimensions.fixed(0.25f, 0.2f),
        PearlfishEntity::createMobAttributes
    )

    val STINGRAY = registerFish(
        "stingray",
        ::StingrayEntity,
        EntityDimensions.fixed(0.75f, 0.2f),
        StingrayEntity::createMobAttributes
    )

    val MANTA_RAY = registerFish(
        "manta_ray",
        ::MantaRayEntity,
        EntityDimensions.fixed(1.0f, 0.3f),
        MantaRayEntity::createMobAttributes
    )

    val PARROTFISH = registerFish(
        "parrotfish",
        ::ParrotfishEntity,
        EntityDimensions.fixed(0.5f, 0.4f),
        ParrotfishEntity::createMobAttributes
    )

    val WRASSE = registerFish(
        "wrasse",
        ::WrasseEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        WrasseEntity::createMobAttributes
    )

    val SURGEONFISH = registerFish(
        "surgeonfish",
        ::SurgeonfishEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        SurgeonfishEntity::createMobAttributes
    )

    val CLOWNFISH = registerFish(
        "clownfish",
        ::ClownfishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        ClownfishEntity::createMobAttributes
    )

    val BOXFISH = registerFish(
        "boxfish",
        ::BoxfishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        BoxfishEntity::createMobAttributes
    )

    val DISCUS = registerFish(
        "discus",
        ::DiscusEntity,
        EntityDimensions.fixed(0.35f, 0.45f),
        DiscusEntity::createMobAttributes
    )

    val DRAGONFISH = registerFishUnderground(
        "dragonfish",
        ::DragonfishEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        DragonfishEntity::createMobAttributes
    )

    val FLASHLIGHT_FISH = registerFish(
        "flashlight_fish",
        ::FlashlightFishEntity,
        EntityDimensions.scalable(0.25f, 0.25f),
        FlashlightFishEntity::createMobAttributes
    )

    val SQUIRRELFISH = registerFish(
        "squirrelfish",
        ::SquirrelfishEntity,
        EntityDimensions.scalable(0.25f, 0.25f),
        SquirrelfishEntity::createMobAttributes
    )

    val GOLDFISH = registerFish(
        "goldfish",
        ::GoldfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GoldfishEntity::createMobAttributes
    )

    val CARP = registerFish(
        "carp",
        ::CarpEntity,
        EntityDimensions.fixed(0.5f, 0.4f),
        CarpEntity::createMobAttributes
    )

    val GOURAMI = registerFish(
        "gourami",
        ::GouramiEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GouramiEntity::createMobAttributes
    )

    val LIONFISH = registerFish(
        "lionfish",
        ::LionfishEntity,
        EntityDimensions.fixed(0.4f, 0.3f),
        LionfishEntity::createMobAttributes
    )

    val MAHI = registerFish(
        "mahi",
        ::MahiEntity,
        EntityDimensions.fixed(0.75f, 0.65f),
        MahiEntity::createMobAttributes
    )

    val MORAY_EEL = registerFish(
        "moray_eel",
        ::MorayEelEntity,
        EntityDimensions.fixed(0.8f, 0.4f),
        MorayEelEntity::createMobAttributes
    )

    val NEEDLEFISH = registerFish(
        "needlefish",
        ::NeedlefishEntity,
        EntityDimensions.fixed(0.4f, 0.25f),
        NeedlefishEntity::createMobAttributes
    )

    val MACKEREL = registerFish(
        "mackerel",
        ::MackerelEntity,
        EntityDimensions.scalable(0.3f, 0.3f),
        MackerelEntity::createMobAttributes
    )

    val HERRING = registerFish(
        "herring",
        ::HerringEntity,
        EntityDimensions.scalable(0.3f, 0.3f),
        HerringEntity::createMobAttributes
    )

    val OSCAR = registerFish(
        "oscar",
        ::OscarEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        OscarEntity::createMobAttributes
    )

    val OPAH = registerFish(
        "opah",
        ::OpahEntity,
        EntityDimensions.fixed(0.8f, 1.0f),
        OpahEntity::createMobAttributes
    )

    val PIRANHA = registerFish(
        "piranha",
        ::PiranhaEntity,
        EntityDimensions.fixed(0.35f, 0.35f),
        PiranhaEntity::createMobAttributes
    )

    val RATFISH = registerFishUnderground(
        "ratfish",
        ::RatfishEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        RatfishEntity::createMobAttributes
    )

    val ROCKFISH = registerFish(
        "rockfish",
        ::RockfishEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        RockfishEntity::createMobAttributes
    )

    val SEA_BASS = registerFish(
        "sea_bass",
        ::SeaBassEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        SeaBassEntity::createMobAttributes
    )

    val JOHN_DORY = registerFishUnderground(
        "john_dory",
        ::JohnDoryEntity,
        EntityDimensions.fixed(0.3f, 0.4f),
        JohnDoryEntity::createMobAttributes
    )

    val SEA_ANGEL = registerFishUnderground(
        "sea_angel",
        ::SeaAngelEntity,
        EntityDimensions.fixed(0.3f, 0.2f),
        SeaAngelEntity::createMobAttributes
    )

    val SEAHORSE = registerFish(
        "seahorse",
        ::SeahorseEntity,
        EntityDimensions.fixed(0.2f, 0.5f),
        SeahorseEntity::createMobAttributes
    )

    val SEADRAGON = registerFish(
        "seadragon",
        ::SeadragonEntity,
        EntityDimensions.fixed(0.6f, 0.3f),
        SeadragonEntity::createMobAttributes
    )

    val STONEFISH = registerFish(
        "stonefish",
        ::StonefishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        StonefishEntity::createMobAttributes
    )

    val SUNFISH = registerFish(
        "sunfish",
        ::SunfishEntity,
        EntityDimensions.fixed(1.25f, 2f),
        SunfishEntity::createMobAttributes
    )

    val TETRA = registerFish(
        "tetra",
        ::TetraEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        TetraEntity::createMobAttributes
    )

    val PUPFISH = registerFish(
        "pupfish",
        ::PupfishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        PupfishEntity::createMobAttributes
    )

    val TIGER_BARB = registerFish(
        "tiger_barb",
        ::TigerBarbEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        TigerBarbEntity::createMobAttributes
    )

    val BLOWFISH = registerFish(
        "blowfish",
        ::BlowfishEntity,
        EntityDimensions.scalable(0.3f, 0.3f),
        BlowfishEntity::createMobAttributes
    )

    val TRIGGERFISH = registerFish(
        "triggerfish",
        ::TriggerfishEntity,
        EntityDimensions.fixed(0.4f, 0.5f),
        TriggerfishEntity::createMobAttributes
    )

    val TUNA = registerFish(
        "tuna",
        ::TunaEntity,
        EntityDimensions.fixed(1.0f, 0.7f),
        TunaEntity::createMobAttributes
    )

    val GOLDEN_DORADO = registerFish(
        "golden_dorado",
        ::GoldenDoradoEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        GoldenDoradoEntity::createMobAttributes
    )

    val OARFISH = registerFishUnderground(
        "oarfish",
        ::OarfishEntity,
        EntityDimensions.scalable(1.5f, 0.65f),
        OarfishEntity::createMobAttributes
    )

    val COELACANTH = registerFishUnderground(
        "coelacanth",
        ::CoelacanthEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        CoelacanthEntity::createMobAttributes
    )

    val DANIO = registerFish(
        "danio",
        ::DanioEntity,
        EntityDimensions.fixed(0.3f, 0.25f),
        DanioEntity::createMobAttributes
    )

    //endregion

    //#region cephalopods

    val ARROW_SQUID = registerCephalopod(
        "arrow_squid",
        ::ArrowSquidEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        ArrowSquidEntity::createMobAttributes
    )

    val FIREFLY_SQUID = registerCephalopod(
        "firefly_squid",
        ::FireflySquidEntity,
        EntityDimensions.fixed(0.35f, 0.3f),
        FireflySquidEntity::createMobAttributes
    )

    val CUTTLEFISH = registerCephalopod(
        "cuttlefish",
        ::CuttlefishEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        CuttlefishEntity::createMobAttributes
    )

    val OCTOPUS = registerCephalopod(
        "octopus",
        ::OctopusEntity,
        EntityDimensions.fixed(0.5f, 0.6f),
        OctopusEntity::createMobAttributes
    )

    val VAMPIRE_SQUID = registerCephalopodUnderground(
        "vampire_squid",
        ::VampireSquidEntity,
        EntityDimensions.fixed(0.6f, 0.4f),
        VampireSquidEntity::createMobAttributes
    )

    val UMBRELLA_OCTOPUS = registerCephalopodUnderground(
        "umbrella_octopus",
        ::UmbrellaOctopusEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        UmbrellaOctopusEntity::createMobAttributes
    )

    val NAUTILUS = registerCephalopodUnderground(
        "nautilus",
        ::NautilusEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        NautilusEntity::createMobAttributes
    )

    //endregion

    //#region crustaceans

    val COCONUT_CRAB = registerCrustacean(
        "coconut_crab",
        ::CoconutCrabEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        CoconutCrabEntity::createMobAttributes
    )

    val DUNGENESS_CRAB = registerCrustacean(
        "dungeness_crab",
        ::DungenessCrabEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        DungenessCrabEntity::createMobAttributes
    )

    val CRAYFISH = registerCrustacean(
        "crayfish",
        ::CrayfishEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        CrayfishEntity::createMobAttributes
    )

    val FIDDLER_CRAB = registerCrustacean(
        "fiddler_crab",
        ::FiddlerCrabEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        FiddlerCrabEntity::createMobAttributes
    )

    val FLOWER_CRAB = registerCrustacean(
        "flower_crab",
        ::FlowerCrabEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        FlowerCrabEntity::createMobAttributes
    )

    val GHOST_CRAB = registerCrustacean(
        "ghost_crab",
        ::GhostCrabEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        GhostCrabEntity::createMobAttributes
    )

    val HERMIT_CRAB = registerCrustacean(
        "hermit_crab",
        ::HermitCrabEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        HermitCrabEntity::createMobAttributes
    )

    val HORSESHOE_CRAB = registerCrustacean(
        "horseshoe_crab",
        ::HorseshoeCrabEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        HorseshoeCrabEntity::createMobAttributes
    )

    val LIGHTFOOT_CRAB = registerCrustacean(
        "lightfoot_crab",
        ::LightfootCrabEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        LightfootCrabEntity::createMobAttributes
    )

    val DECORATOR_CRAB = registerCrustacean(
        "decorator_crab",
        ::DecoratorCrabEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        DecoratorCrabEntity::createMobAttributes
    )

    val LOBSTER = registerCrustacean(
        "lobster",
        ::LobsterEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        LobsterEntity::createMobAttributes
    )

    val SHRIMP = registerCrustacean(
        "shrimp",
        ::ShrimpEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        ShrimpEntity::createMobAttributes
    )

    val VAMPIRE_CRAB = registerCrustacean(
        "vampire_crab",
        ::VampireCrabEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        VampireCrabEntity::createMobAttributes
    )

    val SPIDER_CRAB = registerCrustaceanUnderground(
        "spider_crab",
        ::SpiderCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        SpiderCrabEntity::createMobAttributes
    )

    val YETI_CRAB = registerCrustaceanUnderground(
        "yeti_crab",
        ::YetiCrabEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        YetiCrabEntity::createMobAttributes
    )

    val GIANT_ISOPOD = registerCrustaceanUnderground(
        "giant_isopod",
        ::GiantIsopodEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        GiantIsopodEntity::createMobAttributes
    )

    val KARKINOS = registerMiniboss(
        "karkinos",
        ::KarkinosEntity,
        EntityDimensions.fixed(1.8f, 0.9f),
        KarkinosEntity::createMobAttributes
    )

    val KARCINOGEN = registerMinion(
        "karcinogen",
        ::KarcinogenEntity,
        EntityDimensions.fixed(0.75f, 0.6f),
        KarcinogenEntity::createMobAttributes
    )

    //endregion

    //#region critters
    val SEA_SLUG = registerCritter(
        "sea_slug",
        ::SeaSlugEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        SeaSlugEntity::createMobAttributes
    )

    val SEA_CUCUMBER = registerCritter(
        "sea_cucumber",
        ::SeaCucumberEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        SeaCucumberEntity::createMobAttributes
    )

    val SEA_URCHIN = registerCritter(
        "sea_urchin",
        ::SeaUrchinEntity,
        EntityDimensions.scalable(0.5f, 0.5f),
        SeaUrchinEntity::createMobAttributes
    )

    val STARFISH = registerCritter(
        "starfish",
        ::StarfishEntity,
        EntityDimensions.scalable(0.5f, 0.2f),
        StarfishEntity::createMobAttributes
    )

    //endregion

    //#region jellyfish
    val ATOLLA_JELLYFISH = registerJellyUnderground(
        "atolla_jellyfish",
        ::AtollaJellyfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        AtollaJellyfishEntity::createMobAttributes
    )

    val BIG_RED_JELLYFISH = registerJellyUnderground(
        "big_red_jellyfish",
        ::BigRedJellyfishEntity,
        EntityDimensions.fixed(0.9f, 0.9f),
        BigRedJellyfishEntity::createMobAttributes
    )

    val COSMIC_JELLYFISH = registerJellyUnderground(
        "cosmic_jellyfish",
        ::CosmicJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CosmicJellyfishEntity::createMobAttributes
    )

    val FIREWORK_JELLYFISH = registerJellyUnderground(
        "firework_jellyfish",
        ::FireworkJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        FireworkJellyfishEntity::createMobAttributes
    )

    val BARREL_JELLYFISH = registerJelly(
        "barrel_jellyfish",
        ::BarrelJellyfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        BarrelJellyfishEntity::createMobAttributes
    )

    val BOX_JELLYFISH = registerJelly(
        "box_jellyfish",
        ::BoxJellyfishEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        BoxJellyfishEntity::createMobAttributes
    )

    val BLUE_JELLYFISH = registerJelly(
        "blue_jellyfish",
        ::BlueJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        BlueJellyfishEntity::createMobAttributes
    )

    val CEPHEIDAE_JELLYFISH = registerJelly(
        "cepheidae_jellyfish",
        ::CepheidaeJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CepheidaeJellyfishEntity::createMobAttributes
    )

    val LIONS_MANE_JELLYFISH = registerJelly(
        "lions_mane_jellyfish",
        ::LionsManeJellyfishEntity,
        EntityDimensions.fixed(2.0f, 2.0f),
        LionsManeJellyfishEntity::createMobAttributes
    )

    val MAUVE_STINGER = registerJelly(
        "mauve_stinger",
        ::MauveStingerEntity,
        EntityDimensions.fixed(0.25f, 0.25f),
        MauveStingerEntity::createMobAttributes
    )

    val MOON_JELLYFISH = registerJelly(
        "moon_jellyfish",
        ::MoonJellyfishEntity,
        EntityDimensions.fixed(0.35f, 0.35f),
        MoonJellyfishEntity::createMobAttributes
    )

    val NOMURA_JELLYFISH = registerJelly(
        "nomura_jellyfish",
        ::NomuraJellyfishEntity,
        EntityDimensions.fixed(1.25f, 1.25f),
        NomuraJellyfishEntity::createMobAttributes
    )

    val SEA_NETTLE = registerJelly(
        "sea_nettle",
        ::SeaNettleEntity,
        EntityDimensions.scalable(0.75f, 0.75f),
        SeaNettleEntity::createMobAttributes
    )

    //endregion

    //#region sharks
    val BASKING_SHARK = registerShark(
        "basking_shark",
        ::BaskingSharkEntity,
        EntityDimensions.fixed(2.0f, 0.6f),
        BaskingSharkEntity::createMobAttributes
    )

    val BULL_SHARK = registerShark(
        "bull_shark",
        ::BullSharkEntity,
        EntityDimensions.fixed(1.75f, 0.6f),
        BullSharkEntity::createMobAttributes
    )

    val FRILLED_SHARK = registerSharkUnderground(
        "frilled_shark",
        ::FrilledSharkEntity,
        EntityDimensions.fixed(1.25f, 0.5f),
        FrilledSharkEntity::createMobAttributes
    )

    val HOUND_SHARK = registerShark(
        "hound_shark",
        ::HoundSharkEntity,
        EntityDimensions.fixed(1.0f, 0.3f),
        HoundSharkEntity::createMobAttributes
    )

    val LANTERN_SHARK = registerSharkUnderground(
        "lantern_shark",
        ::LanternSharkEntity,
        EntityDimensions.fixed(0.6f, 0.3f),
        LanternSharkEntity::createMobAttributes
    )

    val GREAT_WHITE_SHARK = registerShark(
        "great_white_shark",
        ::GreatWhiteSharkEntity,
        EntityDimensions.fixed(1.75f, 0.8f),
        GreatWhiteSharkEntity::createMobAttributes
    )

    val HAMMERHEAD_SHARK = registerShark(
        "hammerhead_shark",
        ::HammerheadSharkEntity,
        EntityDimensions.fixed(1.25f, 0.6f),
        HammerheadSharkEntity::createMobAttributes
    )

    val THRESHER_SHARK = registerShark(
        "thresher_shark",
        ::ThresherSharkEntity,
        EntityDimensions.fixed(1.5f, 0.5f),
        ThresherSharkEntity::createMobAttributes
    )

    val TIGER_SHARK = registerShark(
        "tiger_shark",
        ::TigerSharkEntity,
        EntityDimensions.fixed(1.75f, 0.6f),
        TigerSharkEntity::createMobAttributes
    )

    val WHALE_SHARK = registerShark(
        "whale_shark",
        ::WhaleSharkEntity,
        EntityDimensions.fixed(2.5f, 0.8f),
        WhaleSharkEntity::createMobAttributes
    )

    //endregion

    //#region mammals

    val OTTER = registerMammal(
        "otter",
        ::OtterEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        OtterEntity::createMobAttributes
    )

    //endregion

    private fun <T : LivingEntity> registerShark(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("SHARK")
        )
    }

    private fun <T : LivingEntity> registerSharkUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("SHARK_UNDERGROUND")
        )
    }

    private fun <T : LivingEntity> registerCritter(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("CRITTER")
        )
    }

    private fun <T : LivingEntity> registerCrustacean(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("CRUSTACEAN")
        )
    }

    private fun <T : LivingEntity> registerCrustaceanUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("CRUSTACEAN_UNDERGROUND")
        )
    }

    private fun <T : LivingEntity> registerFish(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id, entityFactory, dimensions, attributeContainer,
            Services.PLATFORM.getMobCategoryByName("FISH")
        )
    }

    private fun <T : LivingEntity> registerFishUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("FISH_UNDERGROUND")
        )
    }

    private fun <T : LivingEntity> registerMammal(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            MobCategory.WATER_CREATURE
        )
    }

    private fun <T : LivingEntity> registerCephalopod(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("CEPHALOPOD")
        )
    }

    private fun <T : LivingEntity> registerCephalopodUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("CEPHALOPOD")
        )
    }

    private fun <T : LivingEntity> registerJelly(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("JELLY")
        )
    }

    private fun <T : LivingEntity> registerJellyUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("JELLY_UNDERGROUND")
        )
    }

    private fun <T : LivingEntity> registerMiniboss(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("MINIBOSS")
        )
    }

    private fun <T : LivingEntity> registerMinion(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("MINION")
        )
    }

    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    private fun <T : LivingEntity> registerCustomSpawnGroup(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        hybridAquaticSpawnGroup: MobCategory,
    ): RegistryObject<EntityType<T>> {
        return registerLiving(id, entityFactory, dimensions, attributeContainer, hybridAquaticSpawnGroup)
    }

    /**
     * Registers a living entity to the entity type registry.
     */
    private fun <T : LivingEntity> registerLiving(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        spawnGroup: MobCategory,
    ): RegistryObject<EntityType<T>> {
        val entityType = EntityType.Builder.of(entityFactory, spawnGroup).sized(dimensions.width, dimensions.height)
        return register(id, entityType, attributeContainer)
    }

    private fun <T : LivingEntity> register(
        id: String,
        entity: EntityType.Builder<T>,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return CommonClass.ENTITY_TYPES.register(id) {
            val entityType = entity.build(id)
            Services.PLATFORM.registerAttributes(id, entityType, attributeContainer)
            entityType
        }
    }
}