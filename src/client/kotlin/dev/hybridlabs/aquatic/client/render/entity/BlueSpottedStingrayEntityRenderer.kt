package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.BlueSpottedStingrayEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.renderer.GeoEntityRenderer

class BlueSpottedStingrayEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, BlueSpottedStingrayEntityModel()) {
    override fun render(
        entity: HybridAquaticFishEntity?,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack?,
        bufferSource: VertexConsumerProvider?,
        packedLight: Int
    ) {
        val size = HybridAquaticFishEntity.getScaleAdjustment(entity!!, 0.05f)
        poseStack!!.scale(size, size, size)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}

