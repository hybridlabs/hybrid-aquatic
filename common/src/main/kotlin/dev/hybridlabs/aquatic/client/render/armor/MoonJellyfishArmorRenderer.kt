package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.MoonJellyfishArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class MoonJellyfishArmorRenderer : GeoArmorRenderer<MoonJellyfishArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/moon_jellyfish_armor"))
)