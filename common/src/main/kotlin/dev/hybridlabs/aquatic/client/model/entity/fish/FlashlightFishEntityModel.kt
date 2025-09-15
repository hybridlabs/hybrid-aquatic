package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class FlashlightFishEntityModel : HybridAquaticFishEntityModel<FlashlightFishEntity>("flashlight_fish") {
    override fun getRenderType(animatable: FlashlightFishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getTextureResource(animatable: FlashlightFishEntity): ResourceLocation {
        val isLightOn = animatable.isLightOn

        val texturePath = if (isLightOn) {
            "textures/entity/fish/flashlight_fish/flashlight_fish.png"
        } else {
            "textures/entity/fish/flashlight_fish/flashlight_fish_off.png"
        }

        return CommonClass.locate(texturePath)
    }
}