@file:Suppress("unused")

package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.render.entity.cephalopods.*
import dev.hybridlabs.aquatic.client.render.entity.critter.*
import dev.hybridlabs.aquatic.client.render.entity.crustacean.*
import dev.hybridlabs.aquatic.client.render.entity.fish.*
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.*
import dev.hybridlabs.aquatic.client.render.entity.mammal.KillerWhaleEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.miniboss.KarkinosEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.*
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridAquaticEntityRenderers {
    //region fish
    val AFRICAN_BUTTERFLY = EntityRendererRegistry.register(HybridAquaticEntityTypes.AFRICAN_BUTTERFLY, ::AfricanButterflyEntityRenderer)
    val DAMSELFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.DAMSELFISH, ::DamselfishEntityRenderer)
    val ANGLERFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ANGLERFISH, ::AnglerfishEntityRenderer)
    val DRAGONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.DRAGONFISH, ::DragonfishEntityRenderer)
    val JOHN_DORY = EntityRendererRegistry.register(HybridAquaticEntityTypes.JOHN_DORY, ::JohnDoryEntityRenderer)
    val SNAILFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SNAILFISH, ::SnailfishEntityRenderer)
    val PEARLFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.PEARLFISH, ::PearlfishEntityRenderer)
    val PIRANHA = EntityRendererRegistry.register(HybridAquaticEntityTypes.PIRANHA, ::PiranhaEntityRenderer)
    val BARRELEYE = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARRELEYE, ::BarreleyeEntityRenderer)
    val CLOWNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CLOWNFISH, ::ClownfishEntityRenderer)
    val TUNA = EntityRendererRegistry.register(HybridAquaticEntityTypes.TUNA, ::TunaEntityRenderer)
    val GOLDEN_DORADO = EntityRendererRegistry.register(HybridAquaticEntityTypes.GOLDEN_DORADO, ::GoldenDoradoEntityRenderer)
    val COELACANTH = EntityRendererRegistry.register(HybridAquaticEntityTypes.COELACANTH, ::CoelacanthEntityRenderer)
    val FLASHLIGHT_FISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FLASHLIGHT_FISH, ::FlashlightFishEntityRenderer)
    val SQUIRRELFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SQUIRRELFISH, ::SquirrelfishEntityRenderer)
    val FLYING_FISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FLYING_FISH, ::FlyingFishEntityRenderer)
    val LIONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONFISH, ::LionfishEntityRenderer)
    val OARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OARFISH, ::OarfishEntityRenderer)
    val SEA_ANGEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_ANGEL, ::SeaAngelEntityRenderer)
    val OPAH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OPAH, ::OpahEntityRenderer)
    val SUNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SUNFISH, ::SunfishEntityRenderer)
    val MAHI = EntityRendererRegistry.register(HybridAquaticEntityTypes.MAHI, ::MahiEntityRenderer)
    val MORAY_EEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.MORAY_EEL, ::MorayEelEntityRenderer)
    val ROCKFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ROCKFISH, ::RockfishEntityRenderer)
    val WRECKFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.WRECKFISH, ::WreckfishEntityRenderer)
    val SEA_BASS = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_BASS, ::SeaBassEntityRenderer)
    val TIGER_BARB = EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_BARB, ::TigerBarbEntityRenderer)
    val NEEDLEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NEEDLEFISH, ::NeedlefishEntityRenderer)
    val MACKEREL = EntityRendererRegistry.register(HybridAquaticEntityTypes.MACKEREL, ::MackerelEntityRenderer)
    val RATFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.RATFISH, ::RatfishEntityRenderer)
    val TRIGGERFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.TRIGGERFISH, ::TriggerfishEntityRenderer)
    val OSCAR = EntityRendererRegistry.register(HybridAquaticEntityTypes.OSCAR, ::OscarEntityRenderer)
    val DANIO = EntityRendererRegistry.register(HybridAquaticEntityTypes.DANIO, ::DanioEntityRenderer)
    val TOADFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.TOADFISH, ::ToadfishEntityRenderer)
    val TETRA = EntityRendererRegistry.register(HybridAquaticEntityTypes.TETRA, ::TetraEntityRenderer)
    val STONEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STONEFISH, ::StonefishEntityRenderer)
    val BETTA = EntityRendererRegistry.register(HybridAquaticEntityTypes.BETTA, ::BettaEntityRenderer)
    val GOLDFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.GOLDFISH, ::GoldfishEntityRenderer)
    val SEAHORSE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEAHORSE, ::SeahorseEntityRenderer)
    val SEADRAGON = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEADRAGON, ::SeadragonEntityRenderer)
    val MOON_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.MOON_JELLYFISH, ::MoonJellyfishEntityRenderer)
    val GOURAMI = EntityRendererRegistry.register(HybridAquaticEntityTypes.GOURAMI, ::GouramiEntityRenderer)
    val BOXFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BOXFISH, ::BoxfishEntityRenderer)
    val DISCUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.DISCUS, ::DiscusEntityRenderer)
    val SURGEONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SURGEONFISH, ::SurgeonfishEntityRenderer)
    val PARROTFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.PARROTFISH, ::ParrotfishEntityRenderer)
    val SHEEPSHEAD_WRASSE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SHEEPSHEAD_WRASSE, ::SheepsheadWrasseEntityRenderer)
    val CARP = EntityRendererRegistry.register(HybridAquaticEntityTypes.CARP, ::CarpEntityRenderer)

    //endregion

    //region rays
    val STINGRAY = EntityRendererRegistry.register(HybridAquaticEntityTypes.STINGRAY, ::StingrayEntityRenderer)
    val MANTA_RAY = EntityRendererRegistry.register(HybridAquaticEntityTypes.MANTA_RAY, ::MantaRayEntityRenderer)

    //endregion

    //region rays
    val KILLER_WHALE = EntityRendererRegistry.register(HybridAquaticEntityTypes.KILLER_WHALE, ::KillerWhaleEntityRenderer)

    //endregion

    //region cephalopods
    val CUTTLEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CUTTLEFISH, ::CuttlefishEntityRenderer)
    val UMBRELLA_OCTOPUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS, ::UmbrellaOctopusEntityRenderer)
    val VAMPIRE_SQUID = EntityRendererRegistry.register(HybridAquaticEntityTypes.VAMPIRE_SQUID, ::VampireSquidEntityRenderer)
    val NAUTILUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.NAUTILUS, ::NautilusEntityRenderer)
    val ARROW_SQUID = EntityRendererRegistry.register(HybridAquaticEntityTypes.ARROW_SQUID, ::ArrowSquidEntityRenderer)
    val FIREFLY_SQUID = EntityRendererRegistry.register(HybridAquaticEntityTypes.FIREFLY_SQUID, ::FireflySquidEntityRenderer)
    val GLOWING_SUCKER_OCTOPUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, ::GlowingSuckerOctopusEntityRenderer)

    //endregion

    //region jellyfish
    val SEA_NETTLE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_NETTLE, ::SeaNettleEntityRenderer)
    val FRIED_EGG_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH, ::FriedEggJellyfishEntityRenderer)
    val CAULIFLOWER_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH, ::CauliflowerJellyfishEntityRenderer)
    val NOMURA_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NOMURA_JELLYFISH, ::NomuraJellyfishEntityRenderer)
    val BARREL_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARREL_JELLYFISH, ::BarrelJellyfishEntityRenderer)
    val BLUE_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BLUE_JELLYFISH, ::BlueJellyfishEntityRenderer)
    val MAUVE_STINGER = EntityRendererRegistry.register(HybridAquaticEntityTypes.MAUVE_STINGER, ::MauveStingerEntityRenderer)
    val LIONS_MANE_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, ::LionsManeJellyfishEntityRenderer)
    val ATOLLA_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ATOLLA_JELLYFISH, ::AtollaJellyfishEntityRenderer)
    val BIG_RED_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BIG_RED_JELLYFISH, ::BigRedJellyfishEntityRenderer)
    val COSMIC_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.COSMIC_JELLYFISH, ::CosmicJellyfishEntityRenderer)
    val FIREWORK_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FIREWORK_JELLYFISH, ::FireworkJellyfishEntityRenderer)
    val BOX_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BOX_JELLYFISH, ::BoxJellyfishEntityRenderer)

    //endregion

    //region crustaceans
    val DUNGENESS_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.DUNGENESS_CRAB, ::DungenessCrabEntityRenderer)
    val FIDDLER_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.FIDDLER_CRAB, ::FiddlerCrabEntityRenderer)
    val GHOST_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.GHOST_CRAB, ::GhostCrabEntityRenderer)
    val FLOWER_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.FLOWER_CRAB, ::FlowerCrabEntityRenderer)
    val LIGHTFOOT_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIGHTFOOT_CRAB, ::LightfootCrabEntityRenderer)
    val VAMPIRE_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.VAMPIRE_CRAB, ::VampireCrabEntityRenderer)
    val HORSESHOE_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.HORSESHOE_CRAB, ::HorseshoeCrabEntityRenderer)
    val SPIDER_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.SPIDER_CRAB, ::SpiderCrabEntityRenderer)
    val YETI_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.YETI_CRAB, ::YetiCrabEntityRenderer)
    val DECORATOR_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.DECORATOR_CRAB, ::DecoratorCrabEntityRenderer)
    val GIANT_ISOPOD = EntityRendererRegistry.register(HybridAquaticEntityTypes.GIANT_ISOPOD, ::GiantIsopodEntityRenderer)
    val SHRIMP = EntityRendererRegistry.register(HybridAquaticEntityTypes.SHRIMP, ::ShrimpEntityRenderer)
    val CRAYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CRAYFISH, ::CrayfishEntityRenderer)
    val LOBSTER = EntityRendererRegistry.register(HybridAquaticEntityTypes.LOBSTER, ::LobsterEntityRenderer)
    val COCONUT_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.COCONUT_CRAB, ::CoconutCrabEntityRenderer)
    val HERMIT_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.HERMIT_CRAB, ::HermitCrabEntityRenderer)

    //endregion

    //region critters
    val STARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STARFISH, ::StarfishEntityRenderer)
    val NUDIBRANCH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NUDIBRANCH, ::NudibranchEntityRenderer)
    val SEA_SLUG = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_SLUG, ::SeaSlugEntityRenderer)
    val SEA_CUCUMBER = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_CUCUMBER, ::SeaCucumberEntityRenderer)
    val SEA_URCHIN = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_URCHIN, ::SeaUrchinEntityRenderer)

    //endregion

    //region sharks
    val BULL_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.BULL_SHARK, ::BullSharkEntityRenderer)
    val BASKING_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.BASKING_SHARK, ::BaskingSharkEntityRenderer)
    val THRESHER_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.THRESHER_SHARK, ::ThresherSharkEntityRenderer)
    val FRILLED_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.FRILLED_SHARK, ::FrilledSharkEntityRenderer)
    val LANTERN_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.LANTERN_SHARK, ::LanternSharkEntityRenderer)
    val GREAT_WHITE_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.GREAT_WHITE_SHARK, ::GreatWhiteSharkEntityRenderer)
    val TIGER_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_SHARK, ::TigerSharkEntityRenderer)
    val HAMMERHEAD_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.HAMMERHEAD_SHARK, ::HammerheadSharkEntityRenderer)
    val HOUND_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.HOUND_SHARK, ::HoundSharkEntityRenderer)
    val WHALE_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.WHALE_SHARK, ::WhaleSharkEntityRenderer)
    //endregion

    //region minibosses
    val KARKINOS = EntityRendererRegistry.register(HybridAquaticEntityTypes.KARKINOS, ::KarkinosEntityRenderer)

    //endregion
}