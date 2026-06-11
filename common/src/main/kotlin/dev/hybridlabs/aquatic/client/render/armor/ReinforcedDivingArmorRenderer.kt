package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.DivingArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class ReinforcedDivingArmorRenderer : GeoArmorRenderer<DivingArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/reinforced_diving_armor"))
)