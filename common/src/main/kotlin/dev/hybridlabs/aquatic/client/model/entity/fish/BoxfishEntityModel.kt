package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.BoxfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class BoxfishEntityModel : HybridAquaticFishEntityModel<BoxfishEntity>("boxfish") {
    override fun getRenderType(animatable: BoxfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    private val LONGHORN_COWFISH_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/boxfish/longhorn_cowfish.png")
    private val WHITESPOTTED_BOXFISH_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/boxfish/whitespotted_boxfish.png")

    private val COWFISH_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/fish/boxfish/cowfish.geo.json")
    private val BOXFISH_MODEL = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "geo/fish/boxfish/boxfish.geo.json")

    private val COWFISH_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/cowfish.animation.json")
    private val BOXFISH_ANIMATION = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "animations/boxfish.animation.json")

    override fun getTextureResource(animatable: BoxfishEntity): ResourceLocation {
        return when (animatable.variant) {
            BoxfishEntity.Companion.Type.LONGHORN_COWFISH -> LONGHORN_COWFISH_TEXTURE
            BoxfishEntity.Companion.Type.WHITESPOTTED -> WHITESPOTTED_BOXFISH_TEXTURE
        }
    }

    override fun getModelResource(animatable: BoxfishEntity): ResourceLocation {
        return when (animatable.variant) {
            BoxfishEntity.Companion.Type.LONGHORN_COWFISH -> COWFISH_MODEL
            BoxfishEntity.Companion.Type.WHITESPOTTED -> BOXFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: BoxfishEntity): ResourceLocation {
        return when (animatable.variant) {
            BoxfishEntity.Companion.Type.LONGHORN_COWFISH -> COWFISH_ANIMATION
            BoxfishEntity.Companion.Type.WHITESPOTTED -> BOXFISH_ANIMATION
        }
    }
}
