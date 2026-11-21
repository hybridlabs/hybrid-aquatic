package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.cosmetic.MoonJellyfishArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class MoonJellyfishArmorRenderer : GeoArmorRenderer<MoonJellyfishArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/moon_jellyfish_armor"))
)