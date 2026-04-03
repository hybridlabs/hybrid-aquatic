package dev.hybridlabs.aquatic.client.render.entity.critter

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.client.model.entity.critter.SeaUrchinEntityModel
import dev.hybridlabs.aquatic.entity.critter.HACritterEntity
import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaUrchinEntityRenderer(context: Context) :
    HACritterEntityRenderer<SeaUrchinEntity>(context, SeaUrchinEntityModel(), true) {
    override fun render(
        entity: SeaUrchinEntity,
        entityYaw: Float,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        val size = HACritterEntity.getScaleAdjustment(entity, 0.05f)
        poseStack.scale(size, size, size)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}
