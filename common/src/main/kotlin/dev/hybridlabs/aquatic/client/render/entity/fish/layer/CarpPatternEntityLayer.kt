package dev.hybridlabs.aquatic.client.render.entity.fish.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.client.model.entity.fish.CarpEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.HAFishEntityRenderer
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class CarpPatternEntityLayer(
    renderer: HAFishEntityRenderer<CarpEntity>
) : GeoRenderLayer<CarpEntity>(renderer) {

    private fun getPatternTexture(animatable: CarpEntity, layer: String): ResourceLocation {
        return (geoModel as CarpEntityModel).getPatternTextureResource(animatable, layer)
    }

    override fun render(
        poseStack: PoseStack,
        animatable: CarpEntity,
        bakedModel: BakedGeoModel,
        renderType: RenderType,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if (animatable.getPatternTextureName().isEmpty()) return
        if (animatable.isBaby) return

        val patternTexture = getPatternTexture(animatable, animatable.getPatternTextureName())
        val patternRenderType = RenderType.entityTranslucent(patternTexture)

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, patternRenderType,
            bufferSource.getBuffer(patternRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
            1f, 1f, 1f, 1f)
    }
}