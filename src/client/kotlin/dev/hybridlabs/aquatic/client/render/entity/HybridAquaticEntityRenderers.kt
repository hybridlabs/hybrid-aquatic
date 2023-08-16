package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridAquaticEntityRenderers {
    //region Fish
    val ANGLERFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ANGLERFISH, ::AnglerfishEntityRenderer)
    val ClOWNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CLOWNFISH, ::ClownfishEntityRenderer)
    val BARRELEYE = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARRELEYE, ::BarreleyeEntityRenderer)
    val YELLOWFIN_GERMANY = EntityRendererRegistry.register(HybridAquaticEntityTypes.YELLOWFIN_TUNA, ::YelowfinTunaEntityRenderer)
    val CUTTLEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CUTTLEFISH, ::CuttlefishEntityRenderer)
    val FLASHLIGHT_FISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FLASHLIGHT_FISH, ::FlashlightfishEntityRenderer)
    val LIONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONFISH, ::LionfishEntityRenderer)
    val OARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OARFISH, ::OarfishEntityRenderer)
    val OPAH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OPAH, ::OpahEntityRenderer)
    val PIRANHA = EntityRendererRegistry.register(HybridAquaticEntityTypes.PIRANHA, ::PiranhaEntityRenderer)
    val SEAANGEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_ANGEL, ::SeaAngelEntityRenderer)

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
