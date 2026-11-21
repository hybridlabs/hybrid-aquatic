package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.jellyfish.SeaNettleEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseJellyfishEntityModel
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier

class SeaNettleEntityModel : BaseJellyfishEntityModel<SeaNettleEntity>("hybrid_aquatic", "sea_nettle") {

    companion object {
        private val PACIFIC_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/sea_nettle/pacific_sea_nettle.png")
        private val COMPASS_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/sea_nettle/compass_jellyfish.png")

        private val PACIFIC_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "jellyfish/sea_nettle/sea_nettle")
        private val COMPASS_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "jellyfish/sea_nettle/compass_jellyfish")

        private val PACIFIC_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/jellyfish/sea_nettle/sea_nettle")
        private val COMPASS_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/jellyfish/sea_nettle/compass_jellyfish")
    }
    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SeaNettleEntity.Companion.Type.entries)!!) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_TEXTURE
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(SeaNettleEntity.Companion.Type.entries)!!) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_MODEL
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaNettleEntity): Identifier {
        return when (animatable.variant) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_ANIMATION
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_ANIMATION
        }
    }
}
