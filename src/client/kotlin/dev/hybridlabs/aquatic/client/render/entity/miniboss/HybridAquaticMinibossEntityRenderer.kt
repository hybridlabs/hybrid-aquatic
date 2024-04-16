package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class HybridAquaticMinibossEntityRenderer<T: HybridAquaticMinibossEntity>(
    context: EntityRendererFactory.Context,
    model: GeoModel<T>,
    canGlow: Boolean = false
): GeoEntityRenderer<T>(context, model) {

    init {
        if(canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack,
        bufferSource: VertexConsumerProvider,
        packedLight: Int
    ) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}