package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.HippoEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseMammalEntityModel
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.constant.DataTickets

class HippoEntityModel : BaseMammalEntityModel<HippoEntity>("hybrid_aquatic", "hippo") {

    companion object {
        private val HIPPO_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/hippo/hippo.png")
        private val BABY_HIPPO_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/hippo/baby_hippo.png")

        private val HIPPO_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/mammal/hippo/hippo.geo.json")
        private val BABY_HIPPO_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/mammal/hippo/baby_hippo.geo.json")

        private val HIPPO_ANIMATION =
            ResourceLocation.fromNamespaceAndPath(
                "hybrid_aquatic",
                "animations/entity/mammal/hippo/hippo.animation.json"
            )
    }

    override fun getTextureResource(animatable: HippoEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_HIPPO_TEXTURE
        } else {
            HIPPO_TEXTURE
        }
    }


    override fun getModelResource(animatable: HippoEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_HIPPO_MODEL
        } else {
            HIPPO_MODEL
        }
    }

    override fun getAnimationResource(animatable: HippoEntity): ResourceLocation {
        return HIPPO_ANIMATION
    }

    override fun setCustomAnimations(
        animatable: HippoEntity,
        instanceId: Long,
        animationState: AnimationState<HippoEntity>,
    ) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData!!.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }
}
