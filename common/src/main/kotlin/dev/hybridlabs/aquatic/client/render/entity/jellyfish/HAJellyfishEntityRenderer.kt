package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.entity.jellyfish.HAJellyfishEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.util.Mth
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class HAJellyfishEntityRenderer<T : HAJellyfishEntity>(
    context: Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false
) : GeoEntityRenderer<T>(context, model) {

    init {
        if (canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun applyRotations(jellyfishEntity: T, matrixStack: PoseStack, f: Float, g: Float, h: Float) {
        val i = Mth.lerp(h, jellyfishEntity.prevTiltAngle, jellyfishEntity.tiltAngle)
        matrixStack.translate(0.0f, 0.25f, 0.0f)
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0f - g))
        matrixStack.mulPose(Axis.XP.rotationDegrees(i))
        matrixStack.translate(0.0f, 0.0f, 0.0f)
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
            val size = HAWaterAnimal.getScaleAdjustment(entity, 0.05f)
            poseStack.scale(size, size, size)
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}