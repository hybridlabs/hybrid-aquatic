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

class OrcaSaddleEntityLayer(
    renderer: HADolphinEntityRenderer<OrcaEntity>
) : GeoRenderLayer<OrcaEntity>(renderer) {

    private fun getSaddleTexture(animatable: OrcaEntity, layer: String): ResourceLocation {
        return (geoModel as OrcaEntityModel).getSaddleTextureResource(animatable, layer)
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
        if (animatable.getSaddleTextureName().isEmpty()) return

        val saddleTexture = getSaddleTexture(animatable, animatable.getSaddleTextureName())
        val saddleRenderType = RenderType.entityTranslucent(saddleTexture)

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, saddleRenderType,
            bufferSource.getBuffer(saddleRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
            1)
    }
}