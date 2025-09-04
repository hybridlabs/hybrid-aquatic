package dev.hybridlabs.aquatic.client.renderer.entity.critter.layer

import dev.hybridlabs.aquatic.client.model.entity.critter.HybridAquaticCritterEntityModel
import dev.hybridlabs.aquatic.client.renderer.entity.critter.HybridAquaticCritterEntityRenderer
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import net.minecraft.client.renderer.OverlayTexture
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.VertexConsumer
import net.minecraft.client.renderer.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderType

class HybridAquaticCritterEntityLayer<T: HybridAquaticCritterEntity>(
    renderer: HybridAquaticCritterEntityRenderer<T>
) : GeoRenderType<T>(renderer) {

    private fun getLayerTextureResource(layer: String): ResourceLocation {
        return (geoModel as HybridAquaticCritterEntityModel).getLayerTextureResource(layer)
    }

    override fun render(
        poseStack: MatrixStack,
        animatable: T,
        bakedModel: BakedGeoModel,
        renderType: RenderType,
        bufferSource: VertexConsumerProvider,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if (animatable !is OverlayTextureFeature) return
        if (animatable.getOverlayTextureName().isEmpty()) return

        val layerTexture: ResourceLocation = getLayerTextureResource(animatable.getOverlayTextureName())
        val layerRenderType = RenderType.entityTranslucent(layerTexture)

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, layerRenderType,
            bufferSource.getBuffer(layerRenderType), partialTick, packedLight, OverlayTexture.DEFAULT_UV,
            1f, 1f, 1f, 1f)
    }
}