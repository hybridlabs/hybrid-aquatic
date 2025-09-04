package dev.hybridlabs.aquatic.client.renderer.entity.miniboss

import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.util.math.PoseStack
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class HybridAquaticMinibossEntityRenderer<T: HybridAquaticMinibossEntity>(
    context:.EntityRendererProvider.Context,
    model: GeoModel<T>,
    canGlow: Boolean = false
): GeoEntityRenderer<T>(context, model) {

    init {
        if(canGlow) addRenderType(AutoGlowingGeoLayer(this))
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