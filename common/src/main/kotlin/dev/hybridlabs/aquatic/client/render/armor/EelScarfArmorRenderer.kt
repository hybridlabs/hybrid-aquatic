package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.EelArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import com.geckolib.model.DefaultedItemGeoModel
import com.geckolib.renderer.GeoArmorRenderer

class EelScarfArmorRenderer : GeoArmorRenderer<EelArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/eel_scarf"))
)