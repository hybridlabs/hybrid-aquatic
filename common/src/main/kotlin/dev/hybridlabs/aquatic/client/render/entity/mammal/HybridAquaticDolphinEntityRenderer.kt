package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticDolphinEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer

open class HybridAquaticDolphinEntityRenderer<T: HybridAquaticDolphinEntity>(
    context: EntityRendererFactory.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false
): GeoEntityRenderer<T>(context, model) {

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack,
        bufferSource: VertexConsumerProvider,
        packedLight: Int
    ) {
        if(variableSize) {
            val size = HybridAquaticDolphinEntity.getScaleAdjustment(entity, 0.05f)
            poseStack.scale(size, size, size)
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }

    override fun getDeathMaxRotation(animatable: T): Float {
        return 0f
    }

}