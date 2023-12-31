package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridAquaticEntityRenderers {
    //region Fish
    val ANGLERFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ANGLERFISH, ::AnglerfishEntityRenderer)
    val DRAGONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.DRAGONFISH, ::DragonfishEntityRenderer)
    val PIRANHA = EntityRendererRegistry.register(HybridAquaticEntityTypes.PIRANHA, ::PiranhaEntityRenderer)
    val BARRELEYE = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARRELEYE, ::BarreleyeEntityRenderer)
    val CLOWNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CLOWNFISH, ::ClownfishEntityRenderer)
    val YELLOWFIN_TUNA = EntityRendererRegistry.register(HybridAquaticEntityTypes.YELLOWFIN_TUNA, ::YellowfinTunaEntityRenderer)
    val CUTTLEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CUTTLEFISH, ::CuttlefishEntityRenderer)
    val FLASHLIGHT_FISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FLASHLIGHT_FISH, ::FlashlightfishEntityRenderer)
    val LIONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONFISH, ::LionfishEntityRenderer)
    val OARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OARFISH, ::OarfishEntityRenderer)
    val SEA_ANGEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_ANGEL, ::SeaAngelEntityRenderer)
    val OPAH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OPAH, ::OpahEntityRenderer)
    val SUNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SUNFISH, ::SunfishEntityRenderer)
    val VAMPIRE_SQUID = EntityRendererRegistry.register(HybridAquaticEntityTypes.VAMPIRE_SQUID, ::VampireSquidEntityRenderer)
    val MAHIMAHI = EntityRendererRegistry.register(HybridAquaticEntityTypes.MAHIMAHI, ::MahiMahiEntityRenderer)
    val MORAY_EEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.MORAY_EEL, ::MorayEelEntityRenderer)
    val ROCKFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ROCKFISH, ::RockfishEntityRenderer)
    val TIGER_BARB = EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_BARB, ::TigerBarbEntityRenderer)
    val NEEDLEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NEEDLEFISH, ::NeedlefishEntityRenderer)
    val RATFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.RATFISH, ::RatfishEntityRenderer)
    val NAUTILUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.NAUTILUS, ::NautilusEntityRenderer)
    val TRIGGERFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.TRIGGERFISH, ::TriggerfishEntityRenderer)
    val OSCAR = EntityRendererRegistry.register(HybridAquaticEntityTypes.OSCAR, ::OscarEntityRenderer)
    val UNICORN_FISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.UNICORN_FISH, ::UnicornFishEntityRenderer)
    val ZEBRA_DANIO = EntityRendererRegistry.register(HybridAquaticEntityTypes.ZEBRA_DANIO, ::ZebraDanioEntityRenderer)
    val TOADFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.TOADFISH, ::ToadfishEntityRenderer)
    val TETRA = EntityRendererRegistry.register(HybridAquaticEntityTypes.TETRA, ::TetraEntityRenderer)
    val STONEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STONEFISH, ::StonefishEntityRenderer)
    val BETTA = EntityRendererRegistry.register(HybridAquaticEntityTypes.BETTA, ::BettaEntityRenderer)
    val SEAHORSE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEAHORSE, ::SeahorseEntityRenderer)
    val MOON_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.MOON_JELLYFISH, ::MoonJellyfishEntityRenderer)
    val GOURAMI = EntityRendererRegistry.register(HybridAquaticEntityTypes.GOURAMI, ::GouramiEntityRenderer)
    val COWFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.COWFISH, ::CowfishEntityRenderer)
    val FIREFLY_SQUID = EntityRendererRegistry.register(HybridAquaticEntityTypes.FIREFLY_SQUID, ::FireflySquidEntityRenderer)
    val DISCUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.DISCUS, ::DiscusEntityRenderer)
    val BLUE_TANG = EntityRendererRegistry.register(HybridAquaticEntityTypes.BLUE_TANG, ::BlueTangEntityRenderer)
    val BLUE_SPOTTED_STINGRAY = EntityRendererRegistry.register(HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY, ::BlueSpottedStingrayEntityRenderer)
    val GLOWING_SUCKER_OCTOPUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, ::GlowingSuckerOctopusEntityRenderer)
    val SEA_NETTLE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_NETTLE, ::SeaNettleEntityRenderer)
    val FRIED_EGG_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH, ::FriedEggJellyfishEntityRenderer)
    val CAULIFLOWER_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH, ::CauliflowerJellyfishEntityRenderer)
    val NOMURA_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NOMURA_JELLYFISH, ::NomuraJellyfishEntityRenderer)
    val BARREL_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARREL_JELLYFISH, ::BarrelJellyfishEntityRenderer)
    val COMPASS_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.COMPASS_JELLYFISH, ::CompassJellyfishEntityRenderer)
    val BLUE_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BLUE_JELLYFISH, ::BlueJellyfishEntityRenderer)
    val MAUVE_STINGER = EntityRendererRegistry.register(HybridAquaticEntityTypes.MAUVE_STINGER, ::MauveStingerEntityRenderer)
    val LIONS_MANE_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, ::LionsManeJellyfishEntityRenderer)
    val ATOLLA_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ATOLLA_JELLYFISH, ::AtollaJellyfishEntityRenderer)

    //endregion

    //region critters
    val CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.CRAB, ::CrabEntityRenderer)
    val FIDDLER_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.FIDDLER_CRAB, ::FiddlerCrabEntityRenderer)
    val HERMIT_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.HERMIT_CRAB, ::HermitCrabEntityRenderer)
    val STARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STARFISH, ::StarfishEntityRenderer)
    val NUDIBRANCH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NUDIBRANCH, ::NudibranchEntityRenderer)
    val SEA_CUCUMBER = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_CUCUMBER, ::SeaCucumberEntityRenderer)
    val SEA_URCHIN = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_URCHIN, ::SeaUrchinEntityRenderer)
    val GIANT_CLAM = EntityRendererRegistry.register(HybridAquaticEntityTypes.GIANT_CLAM, ::GiantClamEntityRenderer)
    //endregion

    //region sharks
    val BULL_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.BULL_SHARK, ::BullSharkEntityRenderer)
    val BASKING_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.BASKING_SHARK, ::BaskingSharkEntityRenderer)
    val THRESHER_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.THRESHER_SHARK, ::ThresherSharkEntityRenderer)
    val FRILLED_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.FRILLED_SHARK, ::FrilledSharkEntityRenderer)
    val GREAT_WHITE_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.GREAT_WHITE_SHARK, ::GreatWhiteSharkEntityRenderer)
    val TIGER_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_SHARK, ::TigerSharkEntityRenderer)
    val HAMMERHEAD_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.HAMMERHEAD_SHARK, ::HammerheadSharkEntityRenderer)
    val WHALE_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.WHALE_SHARK, ::WhaleSharkEntityRenderer)
    //endregion
}
