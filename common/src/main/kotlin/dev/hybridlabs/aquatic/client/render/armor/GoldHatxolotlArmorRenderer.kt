package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.GoldHatxolotlArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class GoldHatxolotlArmorRenderer : GeoArmorRenderer<GoldHatxolotlArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/gold_hatxolotl_armor"))
)