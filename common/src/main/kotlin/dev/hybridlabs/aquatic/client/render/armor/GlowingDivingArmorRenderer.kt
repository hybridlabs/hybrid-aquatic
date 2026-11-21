package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.GlowingDivingArmorItem
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

class GlowingDivingArmorRenderer : GeoArmorRenderer<GlowingDivingArmorItem, HumanoidRenderState>(
    DefaultedItemGeoModel(CommonClass.locate("armor/glowing_diving_armor"))) {

    init {
        withRenderLayer(AutoGlowingGeoLayer(this))
    }
}