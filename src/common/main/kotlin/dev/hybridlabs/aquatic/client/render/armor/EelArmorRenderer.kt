package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.EelArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class EelArmorRenderer : GeoArmorRenderer<EelArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/eel_armor"))
)