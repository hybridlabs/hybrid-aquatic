package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.BlowfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BlowfishEntityModel : HAFishEntityModel<BlowfishEntity>("blowfish") {
    override fun getRenderType(animatable: BlowfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getTextureResource(animatable: BlowfishEntity): ResourceLocation {
        val puffState = animatable.getPuffState()

        val texturePath = when (puffState) {
            0 -> "textures/entity/fish/blowfish/blowfish_small.png"
            1 -> "textures/entity/fish/blowfish/blowfish_medium.png"
            else -> "textures/entity/fish/blowfish/blowfish_large.png"
        }

        return CommonClass.locate(texturePath)
    }

    override fun getModelResource(animatable: BlowfishEntity): ResourceLocation {
        val puffState = animatable.getPuffState()

        val texturePath = when (puffState) {
            0 -> "geo/fish/blowfish/blowfish_small.geo.json"
            1 -> "geo/fish/blowfish/blowfish_medium.geo.json"
            else -> "geo/fish/blowfish/blowfish_large.geo.json"
        }

        return CommonClass.locate(texturePath)
    }
}
