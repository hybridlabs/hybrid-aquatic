package dev.hybridlabs.aquatic.client.render.entity.critter

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.client.model.entity.critter.NudibranchEntityModel
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class NudibranchEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<HybridAquaticCritterEntity>(context, NudibranchEntityModel()) {
    override fun render(
        entity: HybridAquaticCritterEntity,
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

