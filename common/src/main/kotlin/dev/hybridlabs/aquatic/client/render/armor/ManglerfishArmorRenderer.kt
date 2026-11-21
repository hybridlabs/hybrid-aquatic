package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.ManglerfishArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class ManglerfishArmorRenderer : GeoArmorRenderer<ManglerfishArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/manglerfish_armor"))
)