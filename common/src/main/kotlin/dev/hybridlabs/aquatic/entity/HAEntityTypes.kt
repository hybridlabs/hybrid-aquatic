package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.cephalopod.*
import dev.hybridlabs.aquatic.entity.critter.*
import dev.hybridlabs.aquatic.entity.crustacean.*
import dev.hybridlabs.aquatic.entity.fish.*
import dev.hybridlabs.aquatic.entity.jellyfish.*
import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
import dev.hybridlabs.aquatic.entity.mammal.ManateeEntity
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import dev.hybridlabs.aquatic.entity.miniboss.*
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import dev.hybridlabs.aquatic.entity.misc.CavitationBubbleEntity
import dev.hybridlabs.aquatic.entity.misc.PrimedDepthChargeEntity
import dev.hybridlabs.aquatic.entity.misc.SmallTNTEntity
import dev.hybridlabs.aquatic.entity.shark.*
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import java.util.concurrent.Callable

@Suppress("SameParameterValue")
object HAEntityTypes {

    //#region All Fish
        //#region River Fish
    val AFRICAN_BUTTERFLYFISH = registerRiverFish(
        "african_butterflyfish",
        ::AfricanButterflyfishEntity,
        EntityDimensions.fixed(0.25f, 0.2f),
        AfricanButterflyfishEntity::createMobAttributes
    )

    val BETTA = registerRiverFish(
        "betta",
        ::BettaEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        BettaEntity::createMobAttributes,
    )

    val DISCUS = registerRiverFish(
        "discus",
        ::DiscusEntity,
        EntityDimensions.fixed(0.35f, 0.45f),
        DiscusEntity::createMobAttributes
    )

    val CORYDORA = registerRiverFish(
        "corydora",
        ::CorydoraEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        CorydoraEntity::createMobAttributes
    )

    val CARP = registerRiverFish(
        "carp",
        ::CarpEntity,
        EntityDimensions.fixed(0.6f, 0.5f),
        CarpEntity::createMobAttributes
    )

    val TROUT = registerRiverFish(
        "trout",
        ::TroutEntity,
        EntityDimensions.scalable(0.4f, 0.4f),
        TroutEntity::createMobAttributes
    )

    val SUNFISH = registerRiverFish(
        "sunfish",
        ::SunfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        SunfishEntity::createMobAttributes
    )

    val SHINER = registerRiverFish(
        "shiner",
        ::ShinerEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        ShinerEntity::createMobAttributes
    )

    val GOURAMI = registerRiverFish(
        "gourami",
        ::GouramiEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GouramiEntity::createMobAttributes
    )

    val PLECO = registerRiverFish(
        "pleco",
        ::PlecoEntity,
        EntityDimensions.fixed(0.8f, 0.5f),
        PlecoEntity::createMobAttributes
    )

    val OSCAR = registerRiverFish(
        "oscar",
        ::OscarEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        OscarEntity::createMobAttributes
    )

    val PIRANHA = registerRiverFish(
        "piranha",
        ::PiranhaEntity,
        EntityDimensions.fixed(0.35f, 0.35f),
        PiranhaEntity::createMobAttributes
    )

    val DANIO = registerRiverFish(
        "danio",
        ::DanioEntity,
        EntityDimensions.fixed(0.3f, 0.25f),
        DanioEntity::createMobAttributes
    )

    val TETRA = registerRiverFish(
        "tetra",
        ::TetraEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        TetraEntity::createMobAttributes
    )

    val PUPFISH = registerRiverFish(
        "pupfish",
        ::PupfishEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        PupfishEntity::createMobAttributes
    )

    val TIGER_BARB = registerRiverFish(
        "tiger_barb",
        ::TigerBarbEntity,
        EntityDimensions.fixed(0.3f, 0.3f),
        TigerBarbEntity::createMobAttributes
    )

    val GOLDEN_DORADO = registerRiverFish(
        "golden_dorado",
        ::GoldenDoradoEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        GoldenDoradoEntity::createMobAttributes
    )
     //#endregion

        //#region Deep Fish
    val ANGLERFISH = registerFishUnderground(
        "anglerfish",
        ::AnglerfishEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        AnglerfishEntity::createMobAttributes
    )

    val VIPERFISH = registerFishUnderground(
        "viperfish",
        ::ViperfishEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        ViperfishEntity::createMobAttributes
    )

    val FANGTOOTH = registerFishUnderground(
        "fangtooth",
        ::FangtoothEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        FangtoothEntity::createMobAttributes
    )

    val HATCHETFISH = registerFishUnderground(
        "hatchetfish",
        ::HatchetfishEntity,
        EntityDimensions.fixed(0.4f, 0.35f),
        HatchetfishEntity::createMobAttributes
    )

    val HAGFISH = registerFishUnderground(
        "hagfish",
        ::HagfishEntity,
        EntityDimensions.fixed(0.5f, 0.4f),
        HagfishEntity::createMobAttributes
    )

    val TRIPOD_FISH = registerFishUnderground(
        "tripod_fish",
        ::TripodFishEntity,
        EntityDimensions.fixed(0.6f, 0.9f),
        TripodFishEntity::createMobAttributes
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
        EntityDimensions.fixed(0.25f, 0.25f),
        BarreleyeEntity::createMobAttributes
    )

    val OARFISH = registerFishUnderground(
        "oarfish",
        ::OarfishEntity,
        EntityDimensions.fixed(1.5f, 0.65f),
        OarfishEntity::createMobAttributes
    )

    val COELACANTH = registerFishUnderground(
        "coelacanth",
        ::CoelacanthEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        CoelacanthEntity::createMobAttributes
    )

    val SLICKHEAD = registerFishUnderground(
        "slickhead",
        ::SlickheadEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        SlickheadEntity::createMobAttributes
    )

    val DRAGONFISH = registerFishUnderground(
        "dragonfish",
        ::DragonfishEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        DragonfishEntity::createMobAttributes
    )

    val SEA_ANGEL = registerFishUnderground(
        "sea_angel",
        ::SeaAngelEntity,
        EntityDimensions.fixed(0.3f, 0.2f),
        SeaAngelEntity::createMobAttributes
    )

    val JOHN_DORY = registerFishUnderground(
        "john_dory",
        ::JohnDoryEntity,
        EntityDimensions.fixed(0.3f, 0.4f),
        JohnDoryEntity::createMobAttributes
    )

    val RATFISH = registerFishUnderground(
        "ratfish",
        ::RatfishEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        RatfishEntity::createMobAttributes
    )
        //#endregion

        //#region Marine Fish
    val BARRACUDA = registerFish(
        "barracuda",
        ::BarracudaEntity,
        EntityDimensions.fixed(1.0f, 0.5f),
        BarracudaEntity::createMobAttributes
    )

    val GARDEN_EEL = registerFish(
        "garden_eel",
        ::GardenEelEntity,
        EntityDimensions.fixed(0.2f, 0.5f),
        GardenEelEntity::createMobAttributes
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

    val OPAH = registerFish(
        "opah",
        ::OpahEntity,
        EntityDimensions.fixed(0.8f, 1.0f),
        OpahEntity::createMobAttributes
    )

    val ROCKFISH = registerFish(
        "rockfish",
        ::RockfishEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        RockfishEntity::createMobAttributes
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

    val SEA_BASS = registerFish(
        "sea_bass",
        ::SeaBassEntity,
        EntityDimensions.fixed(0.4f, 0.4f),
        SeaBassEntity::createMobAttributes
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

    val OCEAN_SUNFISH = registerFish(
        "ocean_sunfish",
        ::OceanSunfishEntity,
        EntityDimensions.fixed(1.25f, 2f),
        OceanSunfishEntity::createMobAttributes
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

    val TREVALLY = registerFish(
        "trevally",
        ::TrevallyEntity,
        EntityDimensions.fixed(0.4f, 0.5f),
        TrevallyEntity::createMobAttributes
    )

    val TUNA = registerFish(
        "tuna",
        ::TunaEntity,
        EntityDimensions.fixed(1.0f, 0.7f),
        TunaEntity::createMobAttributes
    )
        //#endregion
    //#endregion

    //#region All Cephalopods
        //#region Marine Cephalopods
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
        //#endregion

        //#region Deep Cephalopods
    val COLOSSAL_SQUID = registerCephalopodUnderground(
        "colossal_squid",
        ::ColossalSquidEntity,
        EntityDimensions.fixed(3.0f, 1.0f),
        ColossalSquidEntity::createMobAttributes
    )

    val GIANT_SQUID = registerCephalopodUnderground(
        "giant_squid",
        ::GiantSquidEntity,
        EntityDimensions.fixed(3.0f, 1.0f),
        GiantSquidEntity::createMobAttributes
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
        //#endregion
    //#endregion

    //#region All Crustaceans
        //#region Marine Crustaceans
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
        //#endregion

        //#region Deep Crustaceans
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
        //#endregion
    //#endregion

    //#region All Critters
    val SEA_SLUG = registerCritter(
        "sea_slug",
        ::SeaSlugEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        SeaSlugEntity::createMobAttributes
    )

    val SCALYFOOT_SNAIL = registerCritter(
        "scalyfoot_snail",
        ::ScalyfootSnailEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        ScalyfootSnailEntity::createMobAttributes
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

    //#region All Jellyfish
        //#region Marine Jellyfish

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

    val COMB_JELLY = registerJelly(
        "comb_jelly",
        ::CombJellyEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CombJellyEntity::createMobAttributes
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
        //#endregion

        //#region Deep Jellyfish
    val CROWN_JELLYFISH = registerJellyUnderground(
        "crown_jellyfish",
        ::CrownJellyfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        CrownJellyfishEntity::createMobAttributes
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
        //#endregion
    //#endregion

    //#region All Sharks
        //#region Marine Sharks
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

    val SAND_TIGER_SHARK = registerShark(
        "sand_tiger_shark",
        ::SandTigerSharkEntity,
        EntityDimensions.fixed(1.75f, 0.6f),
        SandTigerSharkEntity::createMobAttributes
    )

    val WHALE_SHARK = registerShark(
        "whale_shark",
        ::WhaleSharkEntity,
        EntityDimensions.fixed(2.5f, 0.8f),
        WhaleSharkEntity::createMobAttributes
    )
        //#endregion

        //#region Deep Sharks
    val FRILLED_SHARK = registerSharkUnderground(
        "frilled_shark",
        ::FrilledSharkEntity,
        EntityDimensions.fixed(1.25f, 0.5f),
        FrilledSharkEntity::createMobAttributes
    )

    val SIXGILL_SHARK = registerSharkUnderground(
        "sixgill_shark",
        ::SixgillSharkEntity,
        EntityDimensions.fixed(1.75f, 0.6f),
        SixgillSharkEntity::createMobAttributes
    )

    val SLEEPER_SHARK = registerSharkUnderground(
        "sleeper_shark",
        ::SleeperSharkEntity,
        EntityDimensions.fixed(1.75f, 0.6f),
        SleeperSharkEntity::createMobAttributes
    )
        //#endregion
    //#endregion

    //#region Mammals
    val OTTER = registerMammal(
        "otter",
        ::OtterEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        OtterEntity::createMobAttributes
    )

    val DUGONG = registerSirenian(
        "dugong",
        ::DugongEntity,
        EntityDimensions.fixed(2.0f, 0.9f),
        DugongEntity::createMobAttributes
    )

    val MANATEE = registerSirenian(
        "manatee",
        ::ManateeEntity,
        EntityDimensions.fixed(2.0f, 0.9f),
        ManateeEntity::createMobAttributes
    )

    val ORCA = registerDolphin(
        "orca",
        ::OrcaEntity,
        EntityDimensions.fixed(2.5f, 1.0f),
        OrcaEntity::createMobAttributes
    )
    //#endregion

    //#region Miniboss & Minion
    val KARKINOS = registerMiniboss(
        "karkinos",
        ::KarkinosEntity,
        EntityDimensions.fixed(1.8f, 0.9f),
        KarkinosEntity::createMobAttributes
    )

    val KARCINOGEN = registerMinion(
        "karcinogen",
        ::KarcinogenEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        KarcinogenEntity::createMobAttributes
    )

    val KARCINOMA = registerMinion(
        "karcinoma",
        ::KarcinomaEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        KarcinomaEntity::createMobAttributes
    )

    val SHELL_BEAST = registerMiniboss(
        "shell_beast",
        ::ShellBeastEntity,
        EntityDimensions.fixed(2.5f, 3.0f),
        ShellBeastEntity::createMobAttributes
    )

    val HYPNAUTILUS = registerMinion(
        "hypnautilus",
        ::HypnautilusEntity,
        EntityDimensions.fixed(0.8f, 0.8f),
        HypnautilusEntity::createMobAttributes
    )
    //#endregion

    //#region Misc Entities
    val DEPTH_CHARGE = registerMisc(
        "depth_charge",
        ::PrimedDepthChargeEntity,
        EntityDimensions.fixed(0.98f, 0.98f)
    )

    val SMALL_TNT = registerMisc(
        "small_tnt",
        ::SmallTNTEntity,
        EntityDimensions.fixed(0.49f, 0.49f)
    )

    val CAVITATION_BUBBLE = registerMisc(
        "cavitation_bubble",
        ::CavitationBubbleEntity,
        EntityDimensions.fixed(0.9f, 0.9f)
    )

    val ARGONAUT = registerMisc(
        "argonaut",
        ::ArgonautEntity,
        EntityDimensions.fixed(2.5f, 3.0f),
    )
    //#endregion

    //#region Registration Functions
    //#region Fish Registration
    private fun <T : LivingEntity> registerFish(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 6,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerRiverFish(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 6,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id, entityFactory, dimensions, attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_RIVER_FISH"),
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerFishUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 8,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
            trackingRange,
        )
    }
    //#endregion

    //#region Cephalopod Registration
    private fun <T : LivingEntity> registerCephalopod(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 6,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerCephalopodUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 8,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
            trackingRange,
        )
    }
    //#endregion

    //#region Crustacean Registration
    private fun <T : LivingEntity> registerCrustacean(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 4,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CRUSTACEAN"),
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerCrustaceanUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 8,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CRUSTACEAN"),
            trackingRange,
        )
    }
    //#endregion

    //#region Critter Registration
    private fun <T : LivingEntity> registerCritter(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 4,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CRITTER"),
            trackingRange,
        )
    }
    //#endregion

    //#region Jellyfish Registration
    private fun <T : LivingEntity> registerJelly(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 6,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_JELLY"),
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerJellyUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 8,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_JELLY"),
            trackingRange,
        )
    }
    //#endregion

    //#region Shark Registration
    private fun <T : LivingEntity> registerShark(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 10,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
            trackingRange,
            canSpawnFarFromPlayer = true
        )
    }

    private fun <T : LivingEntity> registerSharkUnderground(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 10,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
            trackingRange,
            canSpawnFarFromPlayer = true
        )
    }
    //#endregion

    //#region Mammal Registration
    private fun <T : LivingEntity> registerMammal(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 10,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MAMMAL"),
            trackingRange,
            canSpawnFarFromPlayer = true
        )
    }

    private fun <T : LivingEntity> registerSirenian(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 10,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MAMMAL"),
            trackingRange,
            canSpawnFarFromPlayer = true
        )
    }

    private fun <T : LivingEntity> registerDolphin(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 10,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MAMMAL"),
            trackingRange,
            canSpawnFarFromPlayer = true
        )
    }
    //#endregion

    //#region Miniboss & Minion Registration
    private fun <T : LivingEntity> registerMiniboss(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 16,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MINIBOSS"),
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerMinion(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 10,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MINION"),
            trackingRange,
        )
    }
    //#endregion

    //#region Misc Entity Registration
    private fun <T : Entity> registerMisc(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
    ): RegistryObject<EntityType<T>> {
        return CommonClass.ENTITY_TYPES.register(id) {
            EntityType.Builder
                .of(entityFactory, MobCategory.MISC)
                .sized(dimensions.width, dimensions.height)
                .clientTrackingRange(10)
                .updateInterval(10)
                .build(id)
        }
    }
    //#endregion
    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    private fun <T : LivingEntity> registerCustomSpawnGroup(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        hybridAquaticSpawnGroup: MobCategory,
        trackingRange: Int = 5,
        updateInterval: Int = 3,
        canSpawnFarFromPlayer: Boolean = false,
    ): RegistryObject<EntityType<T>> {
        return registerLiving(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            hybridAquaticSpawnGroup,
            trackingRange,
            updateInterval,
            canSpawnFarFromPlayer
        )
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
        clientTrackingRange: Int = 5,
        updateInterval: Int = 3,
        canSpawnFarFromPlayer: Boolean = false,
    ): RegistryObject<EntityType<T>> {
        val entityType = EntityType.Builder
            .of(entityFactory, spawnGroup)
            .sized(dimensions.width, dimensions.height)
            .clientTrackingRange(clientTrackingRange)
            .updateInterval(updateInterval)

        if (canSpawnFarFromPlayer) {
            entityType.canSpawnFarFromPlayer()
        }

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
