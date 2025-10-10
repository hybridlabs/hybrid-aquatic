package dev.hybridlabs.aquatic.client.render.entity.crustacean.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.client.model.entity.crustacean.HybridAquaticCrustaceanEntityModel
import dev.hybridlabs.aquatic.client.render.entity.crustacean.HybridAquaticCrustaceanEntityRenderer
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer
import software.bernie.geckolib.util.Color

class HybridAquaticCrustaceanEntityLayer<T : HybridAquaticCrustaceanEntity>(
    renderer: HybridAquaticCrustaceanEntityRenderer<T>
) : GeoRenderLayer<T>(renderer) {

    private fun getLayerTextureResource(layer: String): ResourceLocation {
        return (geoModel as HybridAquaticCrustaceanEntityModel).getLayerTextureResource(layer.lowercase())
    }

    override fun render(
        poseStack: PoseStack,
        animatable: T,
        bakedModel: BakedGeoModel,
        renderType: RenderType?,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer?,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if (animatable !is OverlayTextureFeature) return
        if (animatable.getOverlayTextureName().isEmpty()) return

        val layerTexture: ResourceLocation = getLayerTextureResource(animatable.getOverlayTextureName())
        val layerRenderType = RenderType.entityTranslucent(layerTexture)

        getRenderer().reRender(
            getDefaultBakedModel(animatable),
            poseStack,
            bufferSource,
            animatable,
            layerRenderType,
            bufferSource.getBuffer(layerRenderType),
            partialTick,
            packedLight,
            OverlayTexture.NO_OVERLAY,
            Color.WHITE.argbInt
        )
    }
}