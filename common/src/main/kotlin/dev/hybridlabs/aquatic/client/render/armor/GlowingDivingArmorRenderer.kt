package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.item.armor.GlowingDivingArmorItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class GlowingDivingArmorRenderer : GeoArmorRenderer<GlowingDivingArmorItem>(
    DefaultedItemGeoModel(CommonClass.locate("armor/glowing_diving_armor"))) {

    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}