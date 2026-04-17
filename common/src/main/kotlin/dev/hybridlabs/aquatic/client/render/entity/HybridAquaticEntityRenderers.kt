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
import dev.hybridlabs.aquatic.client.render.entity.misc.CavitationBubbleEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.misc.DepthChargeEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.misc.SmallTNTEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.*
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.platform.ClientServices

@Suppress("unused")
object HybridAquaticEntityRenderers {
    //region fish
    val AFRICAN_BUTTERFLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.AFRICAN_BUTTERFLYFISH,
            ::AfricanButterflyfishEntityRenderer
        )

    val DAMSELFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DAMSELFISH,
            ::DamselfishEntityRenderer
        )

    val ANGLERFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.ANGLERFISH,
            ::AnglerfishEntityRenderer
        )

    val VIPERFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.VIPERFISH,
            ::ViperfishEntityRenderer
        )

    val HATCHETFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.HATCHETFISH,
            ::HatchetfishEntityRenderer
        )

    val TRIPOD_FISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.TRIPOD_FISH,
            ::TripodFishEntityRenderer
        )

    val FANGTOOTH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FANGTOOTH,
            ::FangtoothEntityRenderer
        )

    val DRAGONFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DRAGONFISH,
            ::DragonfishEntityRenderer
        )

    val JOHN_DORY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.JOHN_DORY,
            ::JohnDoryEntityRenderer
        )

    val SNAILFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SNAILFISH,
            ::SnailfishEntityRenderer
        )

    val PEARLFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.PEARLFISH,
            ::PearlfishEntityRenderer
        )

    val PIRANHA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.PIRANHA,
            ::PiranhaEntityRenderer
        )

    val BARRELEYE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BARRELEYE,
            ::BarreleyeEntityRenderer
        )

    val CLOWNFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CLOWNFISH,
            ::ClownfishEntityRenderer
        )

    val GARDEN_EEL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.GARDEN_EEL,
            ::GardenEelEntityRenderer
        )

    val TUNA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.TUNA,
            ::TunaEntityRenderer
        )

    val WRASSE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.WRASSE,
            ::WrasseEntityRenderer
        )

    val GOLDEN_DORADO =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.GOLDEN_DORADO,
            ::GoldenDoradoEntityRenderer
        )

    val COELACANTH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.COELACANTH,
            ::CoelacanthEntityRenderer
        )

    val SLICKHEAD =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SLICKHEAD,
            ::SlickheadEntityRenderer
        )

    val FLASHLIGHT_FISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FLASHLIGHT_FISH,
            ::FlashlightFishEntityRenderer
        )

    val SQUIRRELFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SQUIRRELFISH,
            ::SquirrelfishEntityRenderer
        )

    val FLYING_FISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FLYING_FISH,
            ::FlyingFishEntityRenderer
        )

    val LIONFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.LIONFISH,
            ::LionfishEntityRenderer
        )

    val OARFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.OARFISH,
            ::OarfishEntityRenderer
        )

    val SEA_ANGEL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEA_ANGEL,
            ::SeaAngelEntityRenderer
        )

    val OPAH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.OPAH, ::OpahEntityRenderer
        )


    val OCEAN_SUNFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.OCEAN_SUNFISH, ::OceanSunfishEntityRenderer
        )

    val MAHI =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.MAHI, ::MahiEntityRenderer
        )

    val MORAY_EEL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.MORAY_EEL,
            ::MorayEelEntityRenderer
        )

    val ROCKFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.ROCKFISH,
            ::RockfishEntityRenderer
        )

    val SEA_BASS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEA_BASS, ::SeaBassEntityRenderer
        )

    val TIGER_BARB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.TIGER_BARB,
            ::TigerBarbEntityRenderer
        )

    val NEEDLEFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.NEEDLEFISH,
            ::NeedlefishEntityRenderer
        )

    val BARRACUDA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BARRACUDA,
            ::BarracudaEntityRenderer
        )

    val MACKEREL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.MACKEREL,
            ::MackerelEntityRenderer
        )

    val HERRING =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.HERRING,
            ::HerringEntityRenderer
        )

    val RATFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.RATFISH, ::RatfishEntityRenderer
        )

    val TRIGGERFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.TRIGGERFISH,
            ::TriggerfishEntityRenderer
        )

    val TREVALLY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.TREVALLY,
            ::TrevallyEntityRenderer
        )

    val OSCAR =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.OSCAR, ::OscarEntityRenderer
        )

    val DANIO =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DANIO, ::DanioEntityRenderer
        )

    val BLOWFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BLOWFISH,
            ::BlowfishEntityRenderer
        )

    val TETRA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.TETRA,
            ::TetraEntityRenderer
        )

    val PUPFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.PUPFISH,
            ::PupfishEntityRenderer
        )

    val STONEFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.STONEFISH,
            ::StonefishEntityRenderer
        )

    val BETTA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BETTA,
            ::BettaEntityRenderer
        )

    val SEAHORSE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEAHORSE,
            ::SeahorseEntityRenderer
        )

    val SEADRAGON =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEADRAGON,
            ::SeadragonEntityRenderer
        )

    val MOON_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.MOON_JELLYFISH,
            ::MoonJellyfishEntityRenderer
        )

    val GOURAMI =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.GOURAMI,
            ::GouramiEntityRenderer
        )

    val PLECO =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.PLECO,
            ::PlecoEntityRenderer
        )

    val BOXFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BOXFISH,
            ::BoxfishEntityRenderer
        )

    val DISCUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DISCUS,
            ::DiscusEntityRenderer
        )

    val CORYDORA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CORYDORA,
            ::CorydoraEntityRenderer
        )

    val SURGEONFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SURGEONFISH,
            ::SurgeonfishEntityRenderer
        )

    val PARROTFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.PARROTFISH,
            ::ParrotfishEntityRenderer
        )

    val CARP =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CARP,
            ::CarpEntityRenderer
        )

    val SHINER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SHINER,
            ::ShinerEntityRenderer
        )

    val TROUT =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.TROUT,
            ::TroutEntityRenderer
        )

    val SUNFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SUNFISH,
            ::SunfishEntityRenderer
        )

    //endregion

    //region rays
    val STINGRAY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.STINGRAY,
            ::StingrayEntityRenderer
        )

    val MANTA_RAY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.MANTA_RAY,
            ::MantaRayEntityRenderer
        )

    //endregion

    //region cephalopods
    val CUTTLEFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CUTTLEFISH,
            ::CuttlefishEntityRenderer
        )

    val GIANT_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.GIANT_SQUID,
            ::GiantSquidEntityRenderer
        )

    val COLOSSAL_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.COLOSSAL_SQUID,
            ::ColossalSquidEntityRenderer
        )

    val UMBRELLA_OCTOPUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.UMBRELLA_OCTOPUS,
            ::UmbrellaOctopusEntityRenderer
        )

    val VAMPIRE_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.VAMPIRE_SQUID,
            ::VampireSquidEntityRenderer
        )

    val NAUTILUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.NAUTILUS,
            ::NautilusEntityRenderer
        )

    val ARROW_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.ARROW_SQUID,
            ::ArrowSquidEntityRenderer
        )

    val FIREFLY_SQUID =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FIREFLY_SQUID,
            ::FireflySquidEntityRenderer
        )

    val OCTOPUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.OCTOPUS,
            ::OctopusEntityRenderer
        )

    //endregion

    //region jellyfish
    val SEA_NETTLE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEA_NETTLE,
            ::SeaNettleEntityRenderer
        )

    val CEPHEIDAE_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CEPHEIDAE_JELLYFISH,
            ::CepheidaeJellyfishEntityRenderer
        )

    val NOMURA_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.NOMURA_JELLYFISH,
            ::NomuraJellyfishEntityRenderer
        )

    val BARREL_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BARREL_JELLYFISH,
            ::BarrelJellyfishEntityRenderer
        )

    val BLUE_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BLUE_JELLYFISH,
            ::BlueJellyfishEntityRenderer
        )

    val MAUVE_STINGER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.MAUVE_STINGER,
            ::MauveStingerEntityRenderer
        )

    val LIONS_MANE_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.LIONS_MANE_JELLYFISH,
            ::LionsManeJellyfishEntityRenderer
        )

    val CROWN_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CROWN_JELLYFISH,
            ::CrownJellyfishEntityRenderer
        )

    val BIG_RED_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BIG_RED_JELLYFISH,
            ::BigRedJellyfishEntityRenderer
        )

    val COSMIC_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.COSMIC_JELLYFISH,
            ::CosmicJellyfishEntityRenderer
        )

    val COMB_JELLY =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.COMB_JELLY,
            ::CombJellyEntityRenderer
        )

    val FIREWORK_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FIREWORK_JELLYFISH,
            ::FireworkJellyfishEntityRenderer
        )

    val BOX_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BOX_JELLYFISH,
            ::BoxJellyfishEntityRenderer
        )

    //endregion

    //region crustaceans
    val DUNGENESS_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DUNGENESS_CRAB,
            ::DungenessCrabEntityRenderer
        )

    val FIDDLER_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FIDDLER_CRAB,
            ::FiddlerCrabEntityRenderer
        )

    val GHOST_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.GHOST_CRAB,
            ::GhostCrabEntityRenderer
        )

    val FLOWER_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FLOWER_CRAB,
            ::FlowerCrabEntityRenderer
        )

    val LIGHTFOOT_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.LIGHTFOOT_CRAB,
            ::LightfootCrabEntityRenderer
        )

    val VAMPIRE_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.VAMPIRE_CRAB,
            ::VampireCrabEntityRenderer
        )

    val HORSESHOE_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.HORSESHOE_CRAB,
            ::HorseshoeCrabEntityRenderer
        )

    val SPIDER_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SPIDER_CRAB,
            ::SpiderCrabEntityRenderer
        )

    val YETI_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.YETI_CRAB,
            ::YetiCrabEntityRenderer
        )

    val DECORATOR_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DECORATOR_CRAB,
            ::DecoratorCrabEntityRenderer
        )

    val GIANT_ISOPOD =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.GIANT_ISOPOD,
            ::GiantIsopodEntityRenderer
        )

    val SHRIMP =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SHRIMP, ::ShrimpEntityRenderer
        )

    val CRAYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CRAYFISH,
            ::CrayfishEntityRenderer
        )

    val LOBSTER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.LOBSTER,
            ::LobsterEntityRenderer
        )

    val COCONUT_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.COCONUT_CRAB,
            ::CoconutCrabEntityRenderer
        )

    val HERMIT_CRAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.HERMIT_CRAB,
            ::HermitCrabEntityRenderer
        )

    //endregion

    //region critters
    val STARFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.STARFISH,
            ::StarfishEntityRenderer
        )

    val SEA_SLUG =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEA_SLUG,
            ::SeaSlugEntityRenderer
        )

    val SCALYFOOT_SNAIL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SCALYFOOT_SNAIL,
            ::ScalyfootSnailEntityRenderer
        )

    val SEA_CUCUMBER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEA_CUCUMBER,
            ::SeaCucumberEntityRenderer
        )

    val SEA_URCHIN =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SEA_URCHIN,
            ::SeaUrchinEntityRenderer
        )

    //endregion

    //region sharks
    val BULL_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BULL_SHARK,
            ::BullSharkEntityRenderer
        )

    val BASKING_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.BASKING_SHARK,
            ::BaskingSharkEntityRenderer
        )

    val THRESHER_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.THRESHER_SHARK,
            ::ThresherSharkEntityRenderer
        )

    val FRILLED_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.FRILLED_SHARK,
            ::FrilledSharkEntityRenderer
        )

    val SIXGILL_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SIXGILL_SHARK,
            ::SixgillSharkEntityRenderer
        )

    val SLEEPER_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SLEEPER_SHARK,
            ::SleeperSharkEntityRenderer
        )

    val LANTERN_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.LANTERN_SHARK,
            ::LanternSharkEntityRenderer
        )

    val GREAT_WHITE_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.GREAT_WHITE_SHARK,
            ::GreatWhiteSharkEntityRenderer
        )

    val SAND_TIGER_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SAND_TIGER_SHARK,
            ::SandTigerSharkEntityRenderer
        )

    val HAMMERHEAD_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.HAMMERHEAD_SHARK,
            ::HammerheadSharkEntityRenderer
        )

    val WHALE_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.WHALE_SHARK,
            ::WhaleSharkEntityRenderer
        )

    val HOUND_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.HOUND_SHARK,
            ::HoundSharkEntityRenderer
        )

    //endregion

    //#region Mammals
    val OTTER =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.OTTER,
            ::OtterEntityRenderer
        )

    val DUGONG =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DUGONG,
            ::DugongEntityRenderer
        )

    val MANATEE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.MANATEE,
            ::ManateeEntityRenderer
        )

    val ORCA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.ORCA,
            ::OrcaEntityRenderer
        )

    //region minibosses
    val KARKINOS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.KARKINOS,
            ::KarkinosEntityRenderer
        )

    val KARCINOGEN =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.KARCINOGEN,
            ::KarcinogenEntityRenderer
        )

    val KARCINOMA =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.KARCINOMA,
            ::KarcinomaEntityRenderer
        )

    val SHELL_BEAST =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SHELL_BEAST,
            ::ShellBeastEntityRenderer
        )

    val HYPNAUTILUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.HYPNAUTILUS,
            ::HypnautilusEntityRenderer
        )
    //endregion

    val DEPTH_CHARGE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.DEPTH_CHARGE,
            ::DepthChargeEntityRenderer
        )

    val SMALL_TNT =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.SMALL_TNT,
            ::SmallTNTEntityRenderer
        )

    val ARGONAUT =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.ARGONAUT,
            ::ArgonautEntityRenderer
        )

    val CAVITATION_BUBBLE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.CAVITATION_BUBBLE,
            ::CavitationBubbleEntityRenderer
        )
}
