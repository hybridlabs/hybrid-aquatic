package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RotationAxis
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

open class HybridAquaticJellyfishEntityRenderer<T: HybridAquaticJellyfishEntity>(context: EntityRendererFactory.Context, model: GeoModel<T>, private var variableSize: Boolean = false, canGlow: Boolean = false): GeoEntityRenderer<T>(context, model) {

    init {
        if(canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun applyRotations(jellyfishEntity: T, matrixStack: MatrixStack, f: Float, g: Float, h: Float) {
        val i = MathHelper.lerp(h, jellyfishEntity.prevTiltAngle, jellyfishEntity.tiltAngle)
        val j = MathHelper.lerp(h, jellyfishEntity.prevRollAngle, jellyfishEntity.rollAngle)
        matrixStack.translate(0.0f, 0.25f, 0.0f)
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - g))
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(i))
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j))
        matrixStack.translate(0.0f, 0.0f, 0.0f)
    }

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack,
        bufferSource: VertexConsumerProvider,
        packedLight: Int
    ) {
        if(variableSize) {
            val size = HybridAquaticJellyfishEntity.getScaleAdjustment(entity, 0.05f)
            poseStack.scale(size, size, size)
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}