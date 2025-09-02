@file:Suppress("unused")

package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.render.entity.cephalopods.*
import dev.hybridlabs.aquatic.client.render.entity.critter.NudibranchEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaCucumberEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaUrchinEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.*
import dev.hybridlabs.aquatic.client.render.entity.fish.*
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.*
import dev.hybridlabs.aquatic.client.render.entity.miniboss.KarkinosEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.*
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridAquaticEntityRenderers {
    //region fish
    val AFRICAN_BUTTERFLY = EntityRendererRegistry.register(
        HybridAquaticEntityTypes.AFRICAN_BUTTERFLY.get(),
        ::AfricanButterflyEntityRenderer
    )
    val DAMSELFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.DAMSELFISH.get(), ::DamselfishEntityRenderer)
    val ANGLERFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.ANGLERFISH.get(), ::AnglerfishEntityRenderer)
    val DRAGONFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.DRAGONFISH.get(), ::DragonfishEntityRenderer)
    val JOHN_DORY = EntityRendererRegistry.register(HybridAquaticEntityTypes.JOHN_DORY.get(), ::JohnDoryEntityRenderer)
    val SNAILFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SNAILFISH.get(), ::SnailfishEntityRenderer)
    val PEARLFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.PEARLFISH.get(), ::PearlfishEntityRenderer)
    val PIRANHA = EntityRendererRegistry.register(HybridAquaticEntityTypes.PIRANHA.get(), ::PiranhaEntityRenderer)
    val BARRELEYE = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARRELEYE.get(), ::BarreleyeEntityRenderer)
    val CLOWNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CLOWNFISH.get(), ::ClownfishEntityRenderer)
    val TUNA = EntityRendererRegistry.register(HybridAquaticEntityTypes.TUNA.get(), ::TunaEntityRenderer)
    val GOLDEN_DORADO =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.GOLDEN_DORADO.get(), ::GoldenDoradoEntityRenderer)
    val COELACANTH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.COELACANTH.get(), ::CoelacanthEntityRenderer)
    val FLASHLIGHT_FISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(), ::FlashlightFishEntityRenderer)
    val SQUIRRELFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.SQUIRRELFISH.get(), ::SquirrelfishEntityRenderer)
    val FLYING_FISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.FLYING_FISH.get(), ::FlyingFishEntityRenderer)
    val LIONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONFISH.get(), ::LionfishEntityRenderer)
    val OARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OARFISH.get(), ::OarfishEntityRenderer)
    val SEA_ANGEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_ANGEL.get(), ::SeaAngelEntityRenderer)
    val OPAH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OPAH.get(), ::OpahEntityRenderer)
    val SUNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SUNFISH.get(), ::SunfishEntityRenderer)
    val MAHI = EntityRendererRegistry.register(HybridAquaticEntityTypes.MAHI.get(), ::MahiEntityRenderer)
    val MORAY_EEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.MORAY_EEL.get(), ::MorayEelEntityRenderer)
    val ROCKFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ROCKFISH.get(), ::RockfishEntityRenderer)
    val SEA_BASS = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_BASS.get(), ::SeaBassEntityRenderer)
    val TIGER_BARB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_BARB.get(), ::TigerBarbEntityRenderer)
    val NEEDLEFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.NEEDLEFISH.get(), ::NeedlefishEntityRenderer)
    val MACKEREL = EntityRendererRegistry.register(HybridAquaticEntityTypes.MACKEREL.get(), ::MackerelEntityRenderer)
    val RATFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.RATFISH.get(), ::RatfishEntityRenderer)
    val TRIGGERFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.TRIGGERFISH.get(), ::TriggerfishEntityRenderer)
    val OSCAR = EntityRendererRegistry.register(HybridAquaticEntityTypes.OSCAR.get(), ::OscarEntityRenderer)
    val DANIO = EntityRendererRegistry.register(HybridAquaticEntityTypes.DANIO.get(), ::DanioEntityRenderer)
    val TOADFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.TOADFISH.get(), ::ToadfishEntityRenderer)
    val TETRA = EntityRendererRegistry.register(HybridAquaticEntityTypes.TETRA.get(), ::TetraEntityRenderer)
    val STONEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STONEFISH.get(), ::StonefishEntityRenderer)
    val BETTA = EntityRendererRegistry.register(HybridAquaticEntityTypes.BETTA.get(), ::BettaEntityRenderer)
    val GOLDFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.GOLDFISH.get(), ::GoldfishEntityRenderer)
    val SEAHORSE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEAHORSE.get(), ::SeahorseEntityRenderer)
    val MOON_JELLYFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.MOON_JELLYFISH.get(), ::MoonJellyfishEntityRenderer)
    val GOURAMI = EntityRendererRegistry.register(HybridAquaticEntityTypes.GOURAMI.get(), ::GouramiEntityRenderer)
    val BOXFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BOXFISH.get(), ::BoxfishEntityRenderer)
    val DISCUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.DISCUS.get(), ::DiscusEntityRenderer)
    val SURGEONFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.SURGEONFISH.get(), ::SurgeonfishEntityRenderer)
    val PARROTFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.PARROTFISH.get(), ::ParrotfishEntityRenderer)
    val CARP = EntityRendererRegistry.register(HybridAquaticEntityTypes.CARP.get(), ::CarpEntityRenderer)

    //endregion

    //region rays
    val STINGRAY = EntityRendererRegistry.register(HybridAquaticEntityTypes.STINGRAY.get(), ::StingrayEntityRenderer)
    val MANTA_RAY = EntityRendererRegistry.register(HybridAquaticEntityTypes.MANTA_RAY.get(), ::MantaRayEntityRenderer)

    //endregion

    //region cephalopods
    val CUTTLEFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.CUTTLEFISH.get(), ::CuttlefishEntityRenderer)
    val UMBRELLA_OCTOPUS = EntityRendererRegistry.register(
        HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(),
        ::UmbrellaOctopusEntityRenderer
    )
    val VAMPIRE_SQUID =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), ::VampireSquidEntityRenderer)
    val NAUTILUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.NAUTILUS.get(), ::NautilusEntityRenderer)
    val ARROW_SQUID =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.ARROW_SQUID.get(), ::ArrowSquidEntityRenderer)
    val FIREFLY_SQUID =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.FIREFLY_SQUID.get(), ::FireflySquidEntityRenderer)
    val GLOWING_SUCKER_OCTOPUS = EntityRendererRegistry.register(
        HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS.get(),
        ::GlowingSuckerOctopusEntityRenderer
    )

    //endregion

    //region jellyfish
    val SEA_NETTLE =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_NETTLE.get(), ::SeaNettleEntityRenderer)
    val FRIED_EGG_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH.get(),
            ::FriedEggJellyfishEntityRenderer
        )
    val CAULIFLOWER_JELLYFISH = EntityRendererRegistry.register(
        HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH.get(),
        ::CauliflowerJellyfishEntityRenderer
    )
    val NOMURA_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.NOMURA_JELLYFISH.get(),
            ::NomuraJellyfishEntityRenderer
        )
    val BARREL_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.BARREL_JELLYFISH.get(),
            ::BarrelJellyfishEntityRenderer
        )
    val COMPASS_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.COMPASS_JELLYFISH.get(),
            ::CompassJellyfishEntityRenderer
        )
    val BLUE_JELLYFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.BLUE_JELLYFISH.get(), ::BlueJellyfishEntityRenderer)
    val MAUVE_STINGER =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.MAUVE_STINGER.get(), ::MauveStingerEntityRenderer)
    val LIONS_MANE_JELLYFISH = EntityRendererRegistry.register(
        HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get(),
        ::LionsManeJellyfishEntityRenderer
    )
    val ATOLLA_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH.get(),
            ::AtollaJellyfishEntityRenderer
        )
    val BIG_RED_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.BIG_RED_JELLYFISH.get(),
            ::BigRedJellyfishEntityRenderer
        )
    val COSMIC_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.COSMIC_JELLYFISH.get(),
            ::CosmicJellyfishEntityRenderer
        )
    val FIREWORK_JELLYFISH =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.FIREWORK_JELLYFISH.get(),
            ::FireworkJellyfishEntityRenderer
        )
    val BOX_JELLYFISH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.BOX_JELLYFISH.get(), ::BoxJellyfishEntityRenderer)

    //endregion

    //region crustaceans
    val DUNGENESS_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.DUNGENESS_CRAB.get(), ::DungenessCrabEntityRenderer)
    val FIDDLER_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.FIDDLER_CRAB.get(), ::FiddlerCrabEntityRenderer)
    val GHOST_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.GHOST_CRAB.get(), ::GhostCrabEntityRenderer)
    val FLOWER_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.FLOWER_CRAB.get(), ::FlowerCrabEntityRenderer)
    val LIGHTFOOT_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get(), ::LightfootCrabEntityRenderer)
    val VAMPIRE_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.VAMPIRE_CRAB.get(), ::VampireCrabEntityRenderer)
    val HORSESHOE_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.HORSESHOE_CRAB.get(), ::HorseshoeCrabEntityRenderer)
    val SPIDER_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.SPIDER_CRAB.get(), ::SpiderCrabEntityRenderer)
    val YETI_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.YETI_CRAB.get(), ::YetiCrabEntityRenderer)
    val DECORATOR_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.DECORATOR_CRAB.get(), ::DecoratorCrabEntityRenderer)
    val GIANT_ISOPOD =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.GIANT_ISOPOD.get(), ::GiantIsopodEntityRenderer)
    val SHRIMP = EntityRendererRegistry.register(HybridAquaticEntityTypes.SHRIMP.get(), ::ShrimpEntityRenderer)
    val CRAYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CRAYFISH.get(), ::CrayfishEntityRenderer)
    val LOBSTER = EntityRendererRegistry.register(HybridAquaticEntityTypes.LOBSTER.get(), ::LobsterEntityRenderer)
    val COCONUT_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.COCONUT_CRAB.get(), ::CoconutCrabEntityRenderer)
    val HERMIT_CRAB =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.HERMIT_CRAB.get(), ::HermitCrabEntityRenderer)

    //endregion

    //region critters
    val STARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STARFISH.get(), ::StarfishEntityRenderer)
    val NUDIBRANCH =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.NUDIBRANCH.get(), ::NudibranchEntityRenderer)
    val SEA_CUCUMBER =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_CUCUMBER.get(), ::SeaCucumberEntityRenderer)
    val SEA_URCHIN =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_URCHIN.get(), ::SeaUrchinEntityRenderer)

    //endregion

    //region sharks
    val BULL_SHARK =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.BULL_SHARK.get(), ::BullSharkEntityRenderer)
    val BASKING_SHARK =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.BASKING_SHARK.get(), ::BaskingSharkEntityRenderer)
    val THRESHER_SHARK =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.THRESHER_SHARK.get(), ::ThresherSharkEntityRenderer)
    val FRILLED_SHARK =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.FRILLED_SHARK.get(), ::FrilledSharkEntityRenderer)
    val LANTERN_SHARK =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.LANTERN_SHARK.get(), ::LanternSharkEntityRenderer)
    val GREAT_WHITE_SHARK =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(),
            ::GreatWhiteSharkEntityRenderer
        )
    val TIGER_SHARK =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_SHARK.get(), ::TigerSharkEntityRenderer)
    val HAMMERHEAD_SHARK =
        EntityRendererRegistry.register(
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get(),
            ::HammerheadSharkEntityRenderer
        )
    val WHALE_SHARK =
        EntityRendererRegistry.register(HybridAquaticEntityTypes.WHALE_SHARK.get(), ::WhaleSharkEntityRenderer)
    //endregion

    //region minibosses
    val KARKINOS = EntityRendererRegistry.register(HybridAquaticEntityTypes.KARKINOS.get(), ::KarkinosEntityRenderer)

    //endregion
}