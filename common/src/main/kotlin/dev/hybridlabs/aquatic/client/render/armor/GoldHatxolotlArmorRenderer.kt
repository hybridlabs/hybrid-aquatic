package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.GoldHatxolotlArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import com.geckolib.model.DefaultedItemGeoModel
import com.geckolib.renderer.GeoArmorRenderer

class GoldHatxolotlArmorRenderer : GeoArmorRenderer<GoldHatxolotlArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/gold_hatxolotl_armor"))
)