package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.EelScarfItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class EelArmorRenderer : GeoArmorRenderer<EelScarfItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/eel_armor"))
)