package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.BlueHatxolotlArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class BlueHatxolotlArmorRenderer : GeoArmorRenderer<BlueHatxolotlArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/blue_hatxolotl_armor"))
)