package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.CyanHatxolotlArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class CyanHatxolotlArmorRenderer : GeoArmorRenderer<CyanHatxolotlArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/cyan_hatxolotl_armor"))
)