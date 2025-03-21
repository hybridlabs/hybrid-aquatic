package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib3.model.GeoModel
import software.bernie.geckolib3.renderer.GeoEntityRenderer
import software.bernie.geckolib3.renderer.layer.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class HybridAquaticFishEntityRenderer<T: HybridAquaticFishEntity>(context: EntityRendererFactory.Context, model: GeoModel<T>, private var variableSize: Boolean = false, canGlow: Boolean = false): GeoEntityRenderer<T>(context, model) {

    init {
        if(canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun getDeathMaxRotation(animatable: T): Float {
        return 180f
    }

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack,
        bufferSource: VertexConsumerProvider,
        packedLight: Int
    ) {
        if(variableSize) {
            val size = HybridAquaticFishEntity.getScaleAdjustment(entity, 0.05f)
            poseStack.scale(size, size, size)
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}
