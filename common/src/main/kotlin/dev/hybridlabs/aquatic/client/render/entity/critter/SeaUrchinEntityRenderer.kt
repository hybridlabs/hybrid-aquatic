package dev.hybridlabs.aquatic.client.renderer.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaUrchinEntityModel
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.util.math.PoseStack

class SeaUrchinEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<SeaUrchinEntity>(context, SeaUrchinEntityModel(), true) {
    override fun render(
        entity: SeaUrchinEntity,
        entityYaw: Float,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        val size = HybridAquaticCritterEntity.getScaleAdjustment(entity, 0.05f)
        poseStack.scale(size, size, size)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}
