package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.BrownHatxolotlArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import com.geckolib.model.DefaultedItemGeoModel
import com.geckolib.renderer.GeoArmorRenderer

class BrownHatxolotlArmorRenderer : GeoArmorRenderer<BrownHatxolotlArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/brown_hatxolotl_armor"))
)