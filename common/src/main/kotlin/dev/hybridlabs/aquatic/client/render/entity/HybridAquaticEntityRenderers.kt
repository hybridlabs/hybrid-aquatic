package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.render.entity.cephalopods.ArrowSquidEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.ColossalSquidEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.CuttlefishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.FireflySquidEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.GiantSquidEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.OctopusEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.UmbrellaOctopusEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.ScalyfootSnailEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaCucumberEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaSlugEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaUrchinEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.CoconutCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.CrayfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.DecoratorCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.DungenessCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.FiddlerCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.FlowerCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.GhostCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.GiantIsopodEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.HermitCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.HorseshoeCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.LightfootCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.LobsterEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.ShrimpEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.SpiderCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.VampireCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.YetiCrabEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.AfricanButterflyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.AnglerfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.BarracudaEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.BarreleyeEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.BettaEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.BlowfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.BoxfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.CarpEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.ClownfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.CoelacanthEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.DamselfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.DanioEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.DiscusEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.DragonfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.FangtoothEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.FlashlightFishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.FlyingFishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.GoldenDoradoEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.GoldfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.GouramiEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.HatchetfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.HerringEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.JohnDoryEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.LionfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.MackerelEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.MahiEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.MantaRayEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.MorayEelEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.NeedlefishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.OarfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.OceanSunfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.OpahEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.OscarEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.ParrotfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.PearlfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.PiranhaEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.PlecoEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.PupfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.RatfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.RockfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SeaAngelEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SeaBassEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SeadragonEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SeahorseEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.ShinerEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SnailfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SquirrelfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.StingrayEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.StonefishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SunfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.SurgeonfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.TetraEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.TigerBarbEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.TriggerfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.TroutEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.TunaEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.ViperfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.fish.WrasseEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.BarrelJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.BigRedJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.BlueJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.BoxJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.CepheidaeJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.CosmicJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.CrownJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.FireworkJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.LionsManeJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.MauveStingerEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.MoonJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.NomuraJellyfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.SeaNettleEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.mammal.DugongEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.mammal.OtterEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.miniboss.KarcinogenEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.miniboss.KarcinomaEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.miniboss.KarkinosEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.BaskingSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.BullSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.FrilledSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.GreatWhiteSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.HammerheadSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.HoundSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.LanternSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.ThresherSharkEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.TigerSharkEntityRenderer
import dev.hybridlabs.aquatic.client.renderer.entity.cephalopods.NautilusEntityRenderer
import dev.hybridlabs.aquatic.client.renderer.entity.cephalopods.VampireSquidEntityRenderer
import dev.hybridlabs.aquatic.client.renderer.entity.shark.WhaleSharkEntityRenderer
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
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

    //endregion
}
