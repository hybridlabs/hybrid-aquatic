package dev.hybridlabs.aquatic.client.render.entity.misc.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.client.model.entity.misc.ArgonautEntityModel
import dev.hybridlabs.aquatic.client.render.entity.misc.ArgonautEntityRenderer
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class ArgonautWoodTypeEntityLayer<T: ArgonautEntity>(
    renderer: ArgonautEntityRenderer<T>
): GeoRenderLayer<T>(renderer) {

    private fun getWoodTypeTexture(animatable: T): ResourceLocation {
        return (geoModel as ArgonautEntityModel).getWoodTypeTextureResource(animatable)
    }

    override fun render(
        poseStack: PoseStack,
        animatable: T,
        bakedModel: BakedGeoModel,
        renderType: RenderType,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        val woodTexture = getWoodTypeTexture(animatable)
        val woodRenderType = RenderType.entityTranslucent(woodTexture)
        if (animatable.getWoodType() == ArgonautEntity.WoodType.NONE) return

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, woodRenderType,
            bufferSource.getBuffer(woodRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
            1f, 1f, 1f, 1f)
    }
}