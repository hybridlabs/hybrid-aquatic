package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.MoonJellyfishHatItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class MoonJellyfishArmorRenderer : GeoArmorRenderer<MoonJellyfishHatItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/moon_jellyfish_armor"))
)