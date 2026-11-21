package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.StripedEelScarfItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import com.geckolib.model.DefaultedItemGeoModel
import com.geckolib.renderer.GeoArmorRenderer

class StripedEelArmorRenderer : GeoArmorRenderer<StripedEelScarfItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/striped_eel_armor"))
)