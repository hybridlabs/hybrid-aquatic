package dev.hybridlabs.aquatic.client.render.entity.miniboss

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

open class HybridAquaticMinibossEntityRenderer<T : HybridAquaticMinibossEntity>(
    context: Context,
    model: GeoModel<T>,
    canGlow: Boolean = false
) : GeoEntityRenderer<T>(context, model) {

    init {
        if (canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}