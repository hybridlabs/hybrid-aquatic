package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.DivingArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class ReinforcedDivingArmorRenderer : GeoArmorRenderer<DivingArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/reinforced_diving_armor"))
)