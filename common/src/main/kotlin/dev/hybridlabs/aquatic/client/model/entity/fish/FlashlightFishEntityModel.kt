package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import net.minecraft.resources.ResourceLocation

class FlashlightFishEntityModel : HybridAquaticFishEntityModel<FlashlightFishEntity>("flashlight_fish") {

    override fun getTextureResource(animatable: FlashlightFishEntity): ResourceLocation {
        val isLightOn = animatable.isLightOn

        val texturePath = if (isLightOn) {
            "textures/entity/fish/flashlight_fish/flashlight_fish.png"
        } else {
            "textures/entity/fish/flashlight_fish/flashlight_fish_off.png"
        }

        return ResourceLocation(Constants.MOD_ID, texturePath)
    }
}