package dev.hybridlabs.aquatic.client.render.entity.shark.layer

import dev.hybridlabs.aquatic.client.model.entity.shark.HybridAquaticSharkEntityModel
import dev.hybridlabs.aquatic.client.render.entity.shark.HybridAquaticSharkEntityRenderer
import dev.hybridlabs.aquatic.entity.feature.BodyScarTextureFeature
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class HybridAquaticSharkBodyEntityLayer<T: HybridAquaticSharkEntity>(
    renderer: HybridAquaticSharkEntityRenderer<T>
) : GeoRenderLayer<T>(renderer) {

    private fun getLayerTextureResource(layer: String): Identifier {
        return (geoModel as HybridAquaticSharkEntityModel).getLayerTextureResource(layer)
    }

    override fun render(
        poseStack: MatrixStack,
        animatable: T,
        bakedModel: BakedGeoModel,
        renderType: RenderLayer?,
        bufferSource: VertexConsumerProvider,
        buffer: VertexConsumer?,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if (animatable !is BodyScarTextureFeature) return
        if (animatable.getBodyScarTextureName().isEmpty()) return

        val layerTexture: Identifier = getLayerTextureResource(animatable.getBodyScarTextureName())
        val layerRenderLayer = RenderLayer.getEntityTranslucent(layerTexture)

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, layerRenderLayer,
            bufferSource.getBuffer(layerRenderLayer), partialTick, packedLight, OverlayTexture.DEFAULT_UV,
            1
        )
    }
}