package dev.hybridlabs.aquatic.client.render.entity.critter.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.client.model.entity.critter.StarfishEntityModel
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.core.`object`.Color
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class StarfishColorLayer(
    renderer: StarfishEntityRenderer,
) : GeoRenderLayer<StarfishEntity>(renderer) {

    private fun getLayerTextureResource(animatable: StarfishEntity): ResourceLocation {
        return (geoModel as StarfishEntityModel).getTextureResource(animatable)
    }

    override fun render(
        poseStack: PoseStack,
        animatable: StarfishEntity,
        bakedModel: BakedGeoModel,
        renderType: RenderType,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int,
    ) {
        if (animatable.variant != StarfishEntity.Companion.Type.SMALL &&
            animatable.variant != StarfishEntity.Companion.Type.MEDIUM) {
            return
        }

        val layerTexture: ResourceLocation = getLayerTextureResource(animatable)
        val tintRenderType = RenderType.entityTranslucent(layerTexture)

        val color = Color.ofOpaque(animatable.starfishColor)

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
            color.redFloat,
            color.greenFloat,
            color.blueFloat,
            1f
        )
    }

}