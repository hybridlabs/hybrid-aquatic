package dev.hybridlabs.aquatic.client.render.entity.crustacean

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.entity.crustacean.HACrustaceanEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class HACrustaceanEntityRenderer<T : HACrustaceanEntity>(
    context: Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false
) : GeoEntityRenderer<T>(context, model) {

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    init {
        this.shadowRadius = 0.3f
    }

    init {
        if (canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun getDeathMaxRotation(animatable: T): Float {
        return 0f
    }

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        if (variableSize) {
            val size = HACrustaceanEntity.getScaleAdjustment(entity, 0.05f)
            poseStack.scale(size, size, size)
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}