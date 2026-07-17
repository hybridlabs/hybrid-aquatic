package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.StripedEelScarfItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class StripedEelArmorRenderer : GeoArmorRenderer<StripedEelScarfItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/striped_eel_armor"))
)