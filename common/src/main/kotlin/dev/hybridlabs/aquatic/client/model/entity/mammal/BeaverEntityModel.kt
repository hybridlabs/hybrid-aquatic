package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.BeaverEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseMammalEntityModel
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.constant.DataTickets

class BeaverEntityModel : BaseMammalEntityModel<BeaverEntity>("hybrid_aquatic", "beaver") {

    companion object {
        private val BEAVER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/beaver/beaver.png")
        private val BABY_BEAVER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/beaver/baby_beaver.png")

        private val BEAVER_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/mammal/beaver/beaver.geo.json")
        private val BABY_BEAVER_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/mammal/beaver/baby_beaver.geo.json")

        private val BEAVER_ANIMATION =
            ResourceLocation.fromNamespaceAndPath(
                "hybrid_aquatic",
                "animations/entity/mammal/beaver/beaver.animation.json"
            )
    }

    override fun getTextureResource(animatable: BeaverEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_BEAVER_TEXTURE
        } else {
            BEAVER_TEXTURE
        }
    }


    override fun getModelResource(animatable: BeaverEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_BEAVER_MODEL
        } else {
            BEAVER_MODEL
        }
    }

    override fun getAnimationResource(animatable: BeaverEntity): ResourceLocation {
        return BEAVER_ANIMATION
    }

    override fun setCustomAnimations(
        animatable: BeaverEntity,
        instanceId: Long,
        animationState: AnimationState<BeaverEntity>,
    ) {
        val deltaTime: Float = animationState.partialTick
        val body = animationProcessor.getBone(PartNames.BODY)

        if (!animationState.isMoving && animatable.isInWater && !animatable.onGround() && animatable.getAction() == BeaverEntity.Companion.OtterAction.FLOATING) {
            body?.rotX = Mth.lerp(0.1f, body.rotX, 0f)
        } else {
            val head = animationProcessor.getBone("head")

            if (head != null) {
                val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

                head.rotX = entityData!!.headPitch() * Mth.DEG_TO_RAD
                head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
            }

            val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)
            body?.rotX = xRot * -Mth.DEG_TO_RAD
        }
    }
}
