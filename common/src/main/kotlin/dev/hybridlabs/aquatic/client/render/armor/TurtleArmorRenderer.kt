package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.TurtleArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class TurtleArmorRenderer : GeoArmorRenderer<TurtleArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/turtle_armor"))
)