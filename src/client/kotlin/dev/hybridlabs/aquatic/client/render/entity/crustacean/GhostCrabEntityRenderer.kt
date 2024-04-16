package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GhostCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack

class GhostCrabEntityRenderer(context: Context) : HybridAquaticCrustaceanEntityRenderer<HybridAquaticCrustaceanEntity>(context, GhostCrabEntityModel()) {
    override fun render(
        entity: HybridAquaticCrustaceanEntity?,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack?,
        bufferSource: VertexConsumerProvider?,
        packedLight: Int
    ) {
        val size = HybridAquaticCrustaceanEntity.getScaleAdjustment(entity!!, 0.05f)
        poseStack!!.scale(size, size, size)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}
