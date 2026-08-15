package dev.hybridlabs.aquatic.client.render.entity.critter.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseCritterEntityModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer
import software.bernie.geckolib.util.Color


class StarfishOverlayLayer(
    renderer: StarfishEntityRenderer,
) : GeoRenderLayer<StarfishEntity>(renderer) {


    private fun getLayerTextureResource(layer: String): ResourceLocation {
        return (geoModel as BaseCritterEntityModel).getLayerTextureResource(layer.lowercase())
    }

    override fun render(
        poseStack: PoseStack,
        animatable: StarfishEntity,
        bakedModel: BakedGeoModel,
        renderType: RenderType?,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer?,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        val overlayName = animatable.getOverlayTextureName()
        if (overlayName.isEmpty()) return

        val layerTexture: ResourceLocation = getLayerTextureResource(animatable.getOverlayTextureName())
        val tintRenderType = RenderType.entityTranslucent(layerTexture)

        val color = Color.ofOpaque(animatable.overlayColor)

        getRenderer().reRender(
            getDefaultBakedModel(animatable),
            poseStack,
            bufferSource,
            animatable,
            tintRenderType,
            bufferSource.getBuffer(tintRenderType),
            partialTick,
            packedLight,
            LivingEntityRenderer.getOverlayCoords(animatable, 0.0f),
            color.argbInt
        )
    }

}