package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.PinkHatxolotlArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class PinkHatxolotlArmorRenderer : GeoArmorRenderer<PinkHatxolotlArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/pink_hatxolotl_armor"))
)