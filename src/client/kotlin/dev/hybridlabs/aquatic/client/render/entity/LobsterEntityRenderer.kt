package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.CrabEntityModel
import dev.hybridlabs.aquatic.client.model.entity.LobsterEntityModel
import dev.hybridlabs.aquatic.client.model.entity.ShrimpEntityModel
import dev.hybridlabs.aquatic.client.model.entity.SpiderCrabEntityModel
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.renderer.GeoEntityRenderer

class LobsterEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticCritterEntity>(context, LobsterEntityModel()) {
    override fun render(
        entity: HybridAquaticCritterEntity?,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack?,
        bufferSource: VertexConsumerProvider?,
        packedLight: Int
    ) {
        val size = HybridAquaticCritterEntity.getScaleAdjustment(entity!!, 0.05f)
        poseStack!!.scale(size, size, size)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }

    override fun getMotionAnimThreshold(animatable: HybridAquaticCritterEntity?): Float {
        return 0.0025f
    }
}
