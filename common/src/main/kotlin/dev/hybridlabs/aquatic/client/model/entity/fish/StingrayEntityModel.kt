package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.StingrayEntity
import net.minecraft.resources.Identifier

class StingrayEntityModel : BaseFishEntityModel<StingrayEntity>("hybrid_aquatic", "stingray") {

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(StingrayEntity.Companion.Type.entries)!!) {
            StingrayEntity.Companion.Type.BLUE_SPOTTED -> BLUE_SPOTTED_TEXTURE
            StingrayEntity.Companion.Type.SPOTTED_EAGLE -> SPOTTED_EAGLE_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return when (renderState.getVariant(StingrayEntity.Companion.Type.entries)!!) {
            StingrayEntity.Companion.Type.BLUE_SPOTTED -> BLUE_SPOTTED_MODEL
            StingrayEntity.Companion.Type.SPOTTED_EAGLE -> SPOTTED_EAGLE_MODEL
        }
    }

    override fun getAnimationResource(animatable: StingrayEntity): Identifier {
        return when (animatable.variant) {
            StingrayEntity.Companion.Type.BLUE_SPOTTED -> BLUE_SPOTTED_ANIMATION
            StingrayEntity.Companion.Type.SPOTTED_EAGLE -> SPOTTED_EAGLE_ANIMATION
        }
    }

    companion object {
        private val BLUE_SPOTTED_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/stingray/stingray_blue_spotted.png")
        private val SPOTTED_EAGLE_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/stingray/stingray_spotted_eagle.png")

        private val BLUE_SPOTTED_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/stingray/stingray_blue_spotted")
        private val SPOTTED_EAGLE_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/stingray/stingray_spotted_eagle")

        private val BLUE_SPOTTED_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/stingray/stingray_blue_spotted")
        private val SPOTTED_EAGLE_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/stingray/stingray_spotted_eagle")
    }
}
