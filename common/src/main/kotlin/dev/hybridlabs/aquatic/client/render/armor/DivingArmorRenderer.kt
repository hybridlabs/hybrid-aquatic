package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.DivingArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class DivingArmorRenderer : GeoArmorRenderer<DivingArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/diving_armor"))
)