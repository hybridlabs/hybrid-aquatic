package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.BoxfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BoxfishEntityModel : HybridAquaticFishEntityModel<BoxfishEntity>("boxfish") {
    override fun getRenderType(animatable: BoxfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    private val LONGHORN_COWFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/boxfish/longhorn_cowfish.png")
    private val WHITESPOTTED_BOXFISH_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/boxfish/whitespotted_boxfish.png")

    private val COWFISH_MODEL = Identifier("hybrid-aquatic", "geo/fish/boxfish/cowfish.geo.json")
    private val BOXFISH_MODEL = Identifier("hybrid-aquatic", "geo/fish/boxfish/boxfish.geo.json")

    private val COWFISH_ANIMATION = Identifier("hybrid-aquatic", "animations/cowfish.animation.json")
    private val BOXFISH_ANIMATION = Identifier("hybrid-aquatic", "animations/boxfish.animation.json")

    override fun getTextureResource(animatable: BoxfishEntity): Identifier {
        return when (animatable.variant) {
            BoxfishEntity.Companion.Type.LONGHORN_COWFISH -> LONGHORN_COWFISH_TEXTURE
            BoxfishEntity.Companion.Type.WHITESPOTTED -> WHITESPOTTED_BOXFISH_TEXTURE
        }
    }

    override fun getModelResource(animatable: BoxfishEntity): Identifier {
        return when (animatable.variant) {
            BoxfishEntity.Companion.Type.LONGHORN_COWFISH -> COWFISH_MODEL
            BoxfishEntity.Companion.Type.WHITESPOTTED -> BOXFISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: BoxfishEntity): Identifier {
        return when (animatable.variant) {
            BoxfishEntity.Companion.Type.LONGHORN_COWFISH -> COWFISH_ANIMATION
            BoxfishEntity.Companion.Type.WHITESPOTTED -> BOXFISH_ANIMATION
        }
    }
}
