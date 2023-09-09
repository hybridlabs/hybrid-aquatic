package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.MauveStingerEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class MauveStingerEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticJellyfishEntity>(context, MauveStingerEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
    override fun render(
        entity: HybridAquaticJellyfishEntity?,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack?,
        bufferSource: VertexConsumerProvider?,
        packedLight: Int
    ) {
        val size = HybridAquaticJellyfishEntity.getScaleAdjustment(entity!!, 0.05f)
        poseStack!!.scale(size, size, size)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}