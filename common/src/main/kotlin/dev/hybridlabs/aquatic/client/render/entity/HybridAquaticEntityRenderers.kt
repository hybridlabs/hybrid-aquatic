package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.render.entity.cephalopods.ArrowSquidEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.CuttlefishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.FireflySquidEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.GlowingSuckerOctopusEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaCucumberEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaSlugEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaUrchinEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.*
import dev.hybridlabs.aquatic.client.render.entity.fish.*
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.*
import dev.hybridlabs.aquatic.client.render.entity.mammal.KillerWhaleEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.miniboss.KarkinosEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.*
import dev.hybridlabs.aquatic.client.renderer.entity.cephalopods.NautilusEntityRenderer
import dev.hybridlabs.aquatic.client.renderer.entity.cephalopods.UmbrellaOctopusEntityRenderer
import dev.hybridlabs.aquatic.client.renderer.entity.cephalopods.VampireSquidEntityRenderer
import dev.hybridlabs.aquatic.client.renderer.entity.shark.WhaleSharkEntityRenderer
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.platform.ClientServices

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


    val SUNFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SUNFISH, ::SunfishEntityRenderer
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

    val MACKEREL =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.MACKEREL,
            ::MackerelEntityRenderer
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

    val GLOWING_SUCKER_OCTOPUS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
            ::GlowingSuckerOctopusEntityRenderer
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

    val ATOLLA_JELLYFISH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
            ::AtollaJellyfishEntityRenderer
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

    val NUDIBRANCH =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.SEA_SLUG,
            ::SeaSlugEntityRenderer
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

    val TIGER_SHARK =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.TIGER_SHARK,
            ::TigerSharkEntityRenderer
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

    val KILLER_WHALE =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.KILLER_WHALE,
            ::KillerWhaleEntityRenderer
        )


    //region minibosses
    val KARKINOS =
        ClientServices.PLATFORM.registerEntityRenderer(
            HybridAquaticEntityTypes.KARKINOS,
            ::KarkinosEntityRenderer
        )

    //endregion
}