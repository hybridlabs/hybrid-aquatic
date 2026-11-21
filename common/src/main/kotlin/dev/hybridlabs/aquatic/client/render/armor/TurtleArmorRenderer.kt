package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.TurtleArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class TurtleArmorRenderer : GeoArmorRenderer<TurtleArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/turtle_armor"))
)