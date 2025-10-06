package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState

class OtterEntityModel : HybridAquaticMammalEntityModel<OtterEntity>("otter") {

    private val RIVER_OTTER_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/otter/river_otter.png")
    private val SEA_OTTER_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/otter/sea_otter.png")

    private val RIVER_OTTER_MODEL = ResourceLocation("hybrid-aquatic", "geo/mammal/otter/river_otter.geo.json")
    private val SEA_OTTER_MODEL = ResourceLocation("hybrid-aquatic", "geo/mammal/otter/sea_otter.geo.json")

    private val RIVER_OTTER_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/river_otter.animation.json")
    private val SEA_OTTER_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/sea_otter.animation.json")

    override fun getTextureResource(animatable: OtterEntity): ResourceLocation {
        return when (animatable.variant) {
            OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_TEXTURE
            OtterEntity.Companion.Type.SEA -> SEA_OTTER_TEXTURE
        }
    }

    override fun getModelResource(animatable: OtterEntity): ResourceLocation {
        return when (animatable.variant) {
            OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_MODEL
            OtterEntity.Companion.Type.SEA -> SEA_OTTER_MODEL
        }
    }

    override fun getAnimationResource(animatable: OtterEntity): ResourceLocation {
        return when (animatable.variant) {
            OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_ANIMATION
            OtterEntity.Companion.Type.SEA -> SEA_OTTER_ANIMATION
        }
    }

    override fun setCustomAnimations(
        animatable: OtterEntity,
        instanceId: Long,
        animationState: AnimationState<OtterEntity>
    ) {
        val deltaTime: Float = Minecraft.getInstance().deltaFrameTime
        val body = animationProcessor.getBone(PartNames.BODY)

        if (!animatable.isFloating()) {
            val head = animationProcessor.getBone("head")

            if (head != null) {
                val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

                head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
                head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
            }

            val xRot = Mth.clamp(Mth.lerp(deltaTime, animatable.xRotO, animatable.xRot), -45f, 45f)
            body?.rotX = xRot * -Mth.DEG_TO_RAD
        } else {
            body?.rotX = Mth.lerp(0.1f, body?.rotX ?: 0f, 0f)
        }
    }
}