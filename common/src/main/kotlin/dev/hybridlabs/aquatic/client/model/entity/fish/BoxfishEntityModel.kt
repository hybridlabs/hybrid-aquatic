package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.BoxfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier

class BoxfishEntityModel : BaseFishEntityModel<BoxfishEntity>("hybrid_aquatic", "boxfish") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(BoxfishEntity.Companion.Type.entries)!!) {
            BoxfishEntity.Companion.Type.LONGHORN_COWFISH -> LONGHORN_COWFISH_TEXTURE
            BoxfishEntity.Companion.Type.WHITESPOTTED -> WHITESPOTTED_BOXFISH_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(BoxfishEntity.Companion.Type.entries)!!) {
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

    companion object {
        private val LONGHORN_COWFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/boxfish/longhorn_cowfish.png")
        private val WHITESPOTTED_BOXFISH_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/boxfish/whitespotted_boxfish.png")

        private val COWFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/boxfish/cowfish")
        private val BOXFISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/boxfish/boxfish")

        private val COWFISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/boxfish/cowfish")
        private val BOXFISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/boxfish/boxfish")
    }
}
