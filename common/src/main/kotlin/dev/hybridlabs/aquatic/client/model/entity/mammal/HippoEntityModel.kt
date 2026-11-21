package dev.hybridlabs.aquatic.client.model.entity.mammal

import com.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.mammal.HippoEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseMammalEntityModel
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import com.geckolib.animation.state.AnimationTest
import com.geckolib.constant.DataTickets

class HippoEntityModel : BaseMammalEntityModel<HippoEntity>("hybrid_aquatic", "hippo") {

    companion object {
        private val HIPPO_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/hippo/hippo.png")
        private val BABY_HIPPO_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/hippo/baby_hippo.png")

        private val HIPPO_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/hippo/hippo")
        private val BABY_HIPPO_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/hippo/baby_hippo")

        private val HIPPO_ANIMATION =
            Identifier.fromNamespaceAndPath(
                "hybrid_aquatic",
                "entity/mammal/hippo/hippo"
            )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            BABY_HIPPO_TEXTURE
        } else {
            HIPPO_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            BABY_HIPPO_MODEL
        } else {
            HIPPO_MODEL
        }
    }

    override fun getAnimationResource(animatable: HippoEntity): Identifier {
        return HIPPO_ANIMATION
    }

}
