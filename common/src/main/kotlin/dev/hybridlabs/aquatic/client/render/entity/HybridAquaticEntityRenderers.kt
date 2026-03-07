package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.render.entity.cephalopods.*
import dev.hybridlabs.aquatic.client.render.entity.critter.*
import dev.hybridlabs.aquatic.client.render.entity.crustacean.*
import dev.hybridlabs.aquatic.client.render.entity.fish.*
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.*
import dev.hybridlabs.aquatic.client.render.entity.mammal.DugongEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.mammal.ManateeEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.mammal.OrcaEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.mammal.OtterEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.miniboss.*
import dev.hybridlabs.aquatic.client.render.entity.misc.ArgonautEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.misc.DepthChargeEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.misc.SmallTNTEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.*
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.platform.ClientServices

@Suppress("unused")
object HybridAquaticEntityRenderers {
    //region fish
    val AFRICAN_BUTTERFLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH,
            ::AfricanButterflyfishEntityRenderer
        )

    val DAMSELFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DAMSELFISH,
            ::DamselfishEntityRenderer
        )

    val ANGLERFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.ANGLERFISH,
            ::AnglerfishEntityRenderer
        )

    val VIPERFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.VIPERFISH,
            ::ViperfishEntityRenderer
        )

    val HATCHETFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.HATCHETFISH,
            ::HatchetfishEntityRenderer
        )

    val FANGTOOTH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FANGTOOTH,
            ::FangtoothEntityRenderer
        )

    val DRAGONFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DRAGONFISH,
            ::DragonfishEntityRenderer
        )

    val JOHN_DORY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.JOHN_DORY,
            ::JohnDoryEntityRenderer
        )

    val SNAILFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SNAILFISH,
            ::SnailfishEntityRenderer
        )

    val PEARLFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.PEARLFISH,
            ::PearlfishEntityRenderer
        )

    val PIRANHA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.PIRANHA,
            ::PiranhaEntityRenderer
        )

    val BARRELEYE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BARRELEYE,
            ::BarreleyeEntityRenderer
        )

    val CLOWNFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.CLOWNFISH,
            ::ClownfishEntityRenderer
        )

    val GARDEN_EEL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GARDEN_EEL,
            ::GardenEelEntityRenderer
        )

    val TUNA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.TUNA,
            ::TunaEntityRenderer
        )

    val WRASSE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.WRASSE,
            ::WrasseEntityRenderer
        )

    val GOLDEN_DORADO =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GOLDEN_DORADO,
            ::GoldenDoradoEntityRenderer
        )

    val COELACANTH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.COELACANTH,
            ::CoelacanthEntityRenderer
        )

    val SLICKHEAD =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SLICKHEAD,
            ::SlickheadEntityRenderer
        )

    val FLASHLIGHT_FISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FLASHLIGHT_FISH,
            ::FlashlightFishEntityRenderer
        )

    val SQUIRRELFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SQUIRRELFISH,
            ::SquirrelfishEntityRenderer
        )

    val FLYING_FISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FLYING_FISH,
            ::FlyingFishEntityRenderer
        )

    val LIONFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.LIONFISH,
            ::LionfishEntityRenderer
        )

    val OARFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.OARFISH,
            ::OarfishEntityRenderer
        )

    val SEA_ANGEL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEA_ANGEL,
            ::SeaAngelEntityRenderer
        )

    val OPAH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.OPAH, ::OpahEntityRenderer
        )


    val OCEAN_SUNFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.OCEAN_SUNFISH, ::OceanSunfishEntityRenderer
        )

    val MAHI =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MAHI, ::MahiEntityRenderer
        )

    val MORAY_EEL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MORAY_EEL,
            ::MorayEelEntityRenderer
        )

    val ROCKFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.ROCKFISH,
            ::RockfishEntityRenderer
        )

    val SEA_BASS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEA_BASS, ::SeaBassEntityRenderer
        )

    val TIGER_BARB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.TIGER_BARB,
            ::TigerBarbEntityRenderer
        )

    val NEEDLEFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.NEEDLEFISH,
            ::NeedlefishEntityRenderer
        )

    val BARRACUDA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BARRACUDA,
            ::BarracudaEntityRenderer
        )

    val MACKEREL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MACKEREL,
            ::MackerelEntityRenderer
        )

    val HERRING =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.HERRING,
            ::HerringEntityRenderer
        )

    val RATFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.RATFISH, ::RatfishEntityRenderer
        )

    val TRIGGERFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.TRIGGERFISH,
            ::TriggerfishEntityRenderer
        )

    val OSCAR =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.OSCAR, ::OscarEntityRenderer
        )

    val DANIO =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DANIO, ::DanioEntityRenderer
        )

    val BLOWFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BLOWFISH,
            ::BlowfishEntityRenderer
        )

    val TETRA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.TETRA,
            ::TetraEntityRenderer
        )

    val PUPFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.PUPFISH,
            ::PupfishEntityRenderer
        )

    val STONEFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.STONEFISH,
            ::StonefishEntityRenderer
        )

    val BETTA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BETTA,
            ::BettaEntityRenderer
        )

    val GOLDFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GOLDFISH,
            ::GoldfishEntityRenderer
        )

    val SEAHORSE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEAHORSE,
            ::SeahorseEntityRenderer
        )

    val SEADRAGON =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEADRAGON,
            ::SeadragonEntityRenderer
        )

    val MOON_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MOON_JELLYFISH,
            ::MoonJellyfishEntityRenderer
        )

    val GOURAMI =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GOURAMI,
            ::GouramiEntityRenderer
        )

    val PLECO =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.PLECO,
            ::PlecoEntityRenderer
        )

    val BOXFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BOXFISH,
            ::BoxfishEntityRenderer
        )

    val DISCUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DISCUS,
            ::DiscusEntityRenderer
        )

    val CORYDORA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.CORYDORA,
            ::CorydoraEntityRenderer
        )

    val SURGEONFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SURGEONFISH,
            ::SurgeonfishEntityRenderer
        )

    val PARROTFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.PARROTFISH,
            ::ParrotfishEntityRenderer
        )

    val CARP =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.CARP,
            ::CarpEntityRenderer
        )

    val SHINER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SHINER,
            ::ShinerEntityRenderer
        )

    val TROUT =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.TROUT,
            ::TroutEntityRenderer
        )

    val SUNFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SUNFISH,
            ::SunfishEntityRenderer
        )

    //endregion

    //region rays
    val STINGRAY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.STINGRAY,
            ::StingrayEntityRenderer
        )

    val MANTA_RAY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MANTA_RAY,
            ::MantaRayEntityRenderer
        )

    //endregion

    //region cephalopods
    val CUTTLEFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.CUTTLEFISH,
            ::CuttlefishEntityRenderer
        )

    val GIANT_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GIANT_SQUID,
            ::GiantSquidEntityRenderer
        )

    val COLOSSAL_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.COLOSSAL_SQUID,
            ::ColossalSquidEntityRenderer
        )

    val UMBRELLA_OCTOPUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
            ::UmbrellaOctopusEntityRenderer
        )

    val VAMPIRE_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.VAMPIRE_SQUID,
            ::VampireSquidEntityRenderer
        )

    val NAUTILUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.NAUTILUS,
            ::NautilusEntityRenderer
        )

    val ARROW_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.ARROW_SQUID,
            ::ArrowSquidEntityRenderer
        )

    val FIREFLY_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FIREFLY_SQUID,
            ::FireflySquidEntityRenderer
        )

    val OCTOPUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.OCTOPUS,
            ::OctopusEntityRenderer
        )

    //endregion

    //region jellyfish
    val SEA_NETTLE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEA_NETTLE,
            ::SeaNettleEntityRenderer
        )

    val CEPHEIDAE_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH,
            ::CepheidaeJellyfishEntityRenderer
        )

    val NOMURA_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.NOMURA_JELLYFISH,
            ::NomuraJellyfishEntityRenderer
        )

    val BARREL_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BARREL_JELLYFISH,
            ::BarrelJellyfishEntityRenderer
        )

    val BLUE_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BLUE_JELLYFISH,
            ::BlueJellyfishEntityRenderer
        )

    val MAUVE_STINGER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MAUVE_STINGER,
            ::MauveStingerEntityRenderer
        )

    val LIONS_MANE_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
            ::LionsManeJellyfishEntityRenderer
        )

    val CROWN_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.CROWN_JELLYFISH,
            ::CrownJellyfishEntityRenderer
        )

    val BIG_RED_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BIG_RED_JELLYFISH,
            ::BigRedJellyfishEntityRenderer
        )

    val COSMIC_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.COSMIC_JELLYFISH,
            ::CosmicJellyfishEntityRenderer
        )

    val FIREWORK_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FIREWORK_JELLYFISH,
            ::FireworkJellyfishEntityRenderer
        )

    val BOX_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BOX_JELLYFISH,
            ::BoxJellyfishEntityRenderer
        )

    //endregion

    //region crustaceans
    val DUNGENESS_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DUNGENESS_CRAB,
            ::DungenessCrabEntityRenderer
        )

    val FIDDLER_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FIDDLER_CRAB,
            ::FiddlerCrabEntityRenderer
        )

    val GHOST_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GHOST_CRAB,
            ::GhostCrabEntityRenderer
        )

    val FLOWER_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FLOWER_CRAB,
            ::FlowerCrabEntityRenderer
        )

    val LIGHTFOOT_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
            ::LightfootCrabEntityRenderer
        )

    val VAMPIRE_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.VAMPIRE_CRAB,
            ::VampireCrabEntityRenderer
        )

    val HORSESHOE_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.HORSESHOE_CRAB,
            ::HorseshoeCrabEntityRenderer
        )

    val SPIDER_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SPIDER_CRAB,
            ::SpiderCrabEntityRenderer
        )

    val YETI_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.YETI_CRAB,
            ::YetiCrabEntityRenderer
        )

    val DECORATOR_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DECORATOR_CRAB,
            ::DecoratorCrabEntityRenderer
        )

    val GIANT_ISOPOD =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GIANT_ISOPOD,
            ::GiantIsopodEntityRenderer
        )

    val SHRIMP =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SHRIMP, ::ShrimpEntityRenderer
        )

    val CRAYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.CRAYFISH,
            ::CrayfishEntityRenderer
        )

    val LOBSTER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.LOBSTER,
            ::LobsterEntityRenderer
        )

    val COCONUT_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.COCONUT_CRAB,
            ::CoconutCrabEntityRenderer
        )

    val HERMIT_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.HERMIT_CRAB,
            ::HermitCrabEntityRenderer
        )

    //endregion

    //region critters
    val STARFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.STARFISH,
            ::StarfishEntityRenderer
        )

    val SEA_SLUG =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEA_SLUG,
            ::SeaSlugEntityRenderer
        )

    val SCALYFOOT_SNAIL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SCALYFOOT_SNAIL,
            ::ScalyfootSnailEntityRenderer
        )

    val SEA_CUCUMBER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEA_CUCUMBER,
            ::SeaCucumberEntityRenderer
        )

    val SEA_URCHIN =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEA_URCHIN,
            ::SeaUrchinEntityRenderer
        )

    //endregion

    //region sharks
    val BULL_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BULL_SHARK,
            ::BullSharkEntityRenderer
        )

    val BASKING_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.BASKING_SHARK,
            ::BaskingSharkEntityRenderer
        )

    val THRESHER_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.THRESHER_SHARK,
            ::ThresherSharkEntityRenderer
        )

    val FRILLED_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.FRILLED_SHARK,
            ::FrilledSharkEntityRenderer
        )

    val SIXGILL_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SIXGILL_SHARK,
            ::SixgillSharkEntityRenderer
        )

    val SLEEPER_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SLEEPER_SHARK,
            ::SleeperSharkEntityRenderer
        )

    val LANTERN_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.LANTERN_SHARK,
            ::LanternSharkEntityRenderer
        )

    val GREAT_WHITE_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
            ::GreatWhiteSharkEntityRenderer
        )

    val SAND_TIGER_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SAND_TIGER_SHARK,
            ::SandTigerSharkEntityRenderer
        )

    val HAMMERHEAD_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
            ::HammerheadSharkEntityRenderer
        )

    val WHALE_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.WHALE_SHARK,
            ::WhaleSharkEntityRenderer
        )

    val HOUND_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.HOUND_SHARK,
            ::HoundSharkEntityRenderer
        )

    //endregion

    //#region Mammals
    val OTTER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.OTTER,
            ::OtterEntityRenderer
        )

    val DUGONG =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DUGONG,
            ::DugongEntityRenderer
        )

    val MANATEE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MANATEE,
            ::ManateeEntityRenderer
        )

    val ORCA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.ORCA,
            ::OrcaEntityRenderer
        )

    //region minibosses
    val KARKINOS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.KARKINOS,
            ::KarkinosEntityRenderer
        )

    val KARCINOGEN =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.KARCINOGEN,
            ::KarcinogenEntityRenderer
        )

    val KARCINOMA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.KARCINOMA,
            ::KarcinomaEntityRenderer
        )

    val MANGLERFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MANGLERFISH,
            ::ManglerfishEntityRenderer
        )

    val SHELL_BEAST =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SHELL_BEAST,
            ::ShellBeastEntityRenderer
        )
    //endregion

    val DEPTH_CHARGE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.DEPTH_CHARGE,
            ::DepthChargeEntityRenderer
        )

    val SMALL_TNT =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SMALL_TNT,
            ::SmallTNTEntityRenderer
        )

    val ARGONAUT =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.ARGONAUT,
            ::ArgonautEntityRenderer
        )
}
