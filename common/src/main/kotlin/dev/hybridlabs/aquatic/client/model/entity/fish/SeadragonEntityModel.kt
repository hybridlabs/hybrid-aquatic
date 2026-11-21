package dev.hybridlabs.aquatic.client.model.entity.fish

import com.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import com.geckolib.animation.state.AnimationTest
import com.geckolib.constant.DataTickets

class SeadragonEntityModel : BaseFishEntityModel<SeadragonEntity>("hybrid_aquatic", "seadragon"), HABoneAdjustingModel {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SeadragonEntity.Companion.Type.entries)!!) {
            SeadragonEntity.Companion.Type.LEAFY -> LEAFY_TEXTURE
            SeadragonEntity.Companion.Type.WEEDY -> WEEDY_TEXTURE
            SeadragonEntity.Companion.Type.RUBY -> RUBY_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SeadragonEntity.Companion.Type.entries)!!) {
            SeadragonEntity.Companion.Type.LEAFY -> LEAFY_MODEL
            SeadragonEntity.Companion.Type.WEEDY -> WEEDY_MODEL
            SeadragonEntity.Companion.Type.RUBY -> RUBY_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeadragonEntity): Identifier {
        return when (animatable.variant) {
            SeadragonEntity.Companion.Type.LEAFY -> LEAFY_ANIMATION
            SeadragonEntity.Companion.Type.WEEDY -> WEEDY_ANIMATION
            SeadragonEntity.Companion.Type.RUBY -> RUBY_ANIMATION
        }
    }

    override fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {
        val headPitch = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f
        val netHeadYaw = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_YAW, 0f) ?: 0f

        snapshots.get("head").ifPresent { head ->
            head.setRotX(headPitch * Mth.DEG_TO_RAD)
            head.setRotY(netHeadYaw * Mth.DEG_TO_RAD)
        }
    }

    companion object {
        private val LEAFY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/seadragon/leafy_seadragon.png")
        private val WEEDY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/seadragon/weedy_seadragon.png")
        private val RUBY_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/seadragon/ruby_seadragon.png")

        private val LEAFY_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/seadragon/leafy_seadragon")
        private val WEEDY_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/seadragon/weedy_seadragon")
        private val RUBY_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/seadragon/ruby_seadragon")

        private val LEAFY_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/seadragon/seadragon_leafy")
        private val WEEDY_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/seadragon/seadragon_weedy")
        private val RUBY_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/seadragon/seadragon_ruby")
    }
}
