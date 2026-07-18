package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.EelArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class EelScarfArmorRenderer : GeoArmorRenderer<EelArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/eel_scarf"))
)