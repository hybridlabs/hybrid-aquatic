package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.ManglerfishArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class ManglerfishArmorRenderer : GeoArmorRenderer<ManglerfishArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/manglerfish_armor"))
)