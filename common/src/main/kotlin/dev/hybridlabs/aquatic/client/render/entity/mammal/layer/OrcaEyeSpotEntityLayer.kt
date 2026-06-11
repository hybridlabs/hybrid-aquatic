package dev.hybridlabs.aquatic.client.render.entity.mammal.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.client.model.entity.mammal.OrcaEntityModel
import dev.hybridlabs.aquatic.client.render.entity.mammal.HADolphinEntityRenderer
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class OrcaEyeSpotEntityLayer(
    renderer: HADolphinEntityRenderer<OrcaEntity>
) : GeoRenderLayer<OrcaEntity>(renderer) {

    private fun getEyeSpotTexture(animatable: OrcaEntity, layer: String): ResourceLocation {
        return (geoModel as OrcaEntityModel).getEyeSpotTextureResource(animatable, layer)
    }

    override fun render(
        poseStack: PoseStack,
        animatable: OrcaEntity,
        bakedModel: BakedGeoModel,
        renderType: RenderType?,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer?,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if (animatable.getEyeSpotTextureName().isEmpty()) return

        val eyeSpotTexture = getEyeSpotTexture(animatable, animatable.getEyeSpotTextureName())
        val eyeSpotRenderType = RenderType.entityTranslucent(eyeSpotTexture)

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, eyeSpotRenderType,
            bufferSource.getBuffer(eyeSpotRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
            1)
    }
}