package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.DivingArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class SeashellArmorRenderer : GeoArmorRenderer<DivingArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/seashell_armor"))
)