package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.TroutEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.Identifier

class TroutEntityModel : BaseFishEntityModel<TroutEntity>("hybrid_aquatic", "trout") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(TroutEntity.Companion.Type.entries)!!) {
            TroutEntity.Companion.Type.BULL_TROUT -> BULL_TROUT_TEXTURE
            TroutEntity.Companion.Type.REDBAND_TROUT -> REDBAND_TROUT_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(TroutEntity.Companion.Type.entries)!!) {
            TroutEntity.Companion.Type.BULL_TROUT -> BULL_TROUT_MODEL
            TroutEntity.Companion.Type.REDBAND_TROUT -> REDBAND_TROUT_MODEL
        }
    }

    override fun getAnimationResource(animatable: TroutEntity): Identifier {
        return when (animatable.variant) {
            TroutEntity.Companion.Type.BULL_TROUT -> BULL_TROUT_ANIMATION
            TroutEntity.Companion.Type.REDBAND_TROUT -> REDBAND_TROUT_ANIMATION
        }
    }

    companion object {
        private val BULL_TROUT_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/trout/bull_trout.png")
        private val REDBAND_TROUT_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/trout/redband_trout.png")

        private val BULL_TROUT_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/trout/bull_trout")
        private val REDBAND_TROUT_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/trout/redband_trout")

        private val BULL_TROUT_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/trout/bull_trout")
        private val REDBAND_TROUT_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/trout/redband_trout")
    }
}
