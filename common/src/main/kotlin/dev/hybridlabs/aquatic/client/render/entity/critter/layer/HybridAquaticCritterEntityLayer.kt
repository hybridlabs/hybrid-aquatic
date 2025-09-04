package dev.hybridlabs.aquatic.client.render.entity.critter.layer

import dev.hybridlabs.aquatic.client.model.entity.critter.HybridAquaticCritterEntityModel
import dev.hybridlabs.aquatic.client.render.entity.critter.HybridAquaticCritterEntityRenderer
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class HybridAquaticCritterEntityLayer<T: HybridAquaticCritterEntity>(
    renderer: HybridAquaticCritterEntityRenderer<T>
) : GeoRenderLayer<T>(renderer) {

    private fun getLayerTextureResource(layer: String): Identifier {
        return (geoModel as HybridAquaticCritterEntityModel).getLayerTextureResource(layer)
    }

    override fun render(
        poseStack: MatrixStack,
        animatable: T,
        bakedModel: BakedGeoModel,
        renderType: RenderLayer,
        bufferSource: VertexConsumerProvider,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if (animatable !is OverlayTextureFeature) return
        if (animatable.getOverlayTextureName().isEmpty()) return

        val layerTexture: Identifier = getLayerTextureResource(animatable.getOverlayTextureName())
        val layerRenderLayer = RenderLayer.getEntityTranslucent(layerTexture)

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, layerRenderLayer,
            bufferSource.getBuffer(layerRenderLayer), partialTick, packedLight, OverlayTexture.DEFAULT_UV,
            1f, 1f, 1f, 1f)
    }
}