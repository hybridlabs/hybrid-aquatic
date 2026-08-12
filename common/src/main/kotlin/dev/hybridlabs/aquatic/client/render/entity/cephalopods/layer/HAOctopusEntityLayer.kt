package dev.hybridlabs.aquatic.client.render.entity.cephalopods.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.entity.cephalopod.OctopusEntity
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.hapi.client.model.entity.BaseOctopusEntityModel
import dev.hybridlabs.hapi.client.render.entity.BaseOctopusEntityRenderer
import dev.hybridlabs.hapi.entity.water.base.BaseOctopusEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer
import software.bernie.geckolib.util.Color


class HAOctopusEntityLayer<T: BaseOctopusEntity>(
    renderer: BaseOctopusEntityRenderer<T>,
) : GeoRenderLayer<T>(renderer) {

    private fun getLayerTextureResource(): ResourceLocation {
        return (geoModel as BaseOctopusEntityModel).getLayerTextureResource()
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

        if (animatable is OctopusEntity &&
            animatable.variant != OctopusEntity.Companion.Type.OCTOPUS) {
            return
        }

        if (!animatable.isSitting()) return

        val overlayName = animatable.getOverlayTextureName()
        if (overlayName.isEmpty()) return

        val layerTexture = getLayerTextureResource()
        val tintRenderType = RenderType.entityTranslucent(layerTexture)

        var current = Color.ofOpaque(animatable.getCurrentColor())
        val target = Color.ofOpaque(animatable.getTargetColor())

        if (current != target) {
            val blendSpeed = 0.025f
            current = Color.ofRGB(
                Mth.lerp(blendSpeed, current.redFloat, target.redFloat),
                Mth.lerp(blendSpeed, current.greenFloat, target.greenFloat),
                Mth.lerp(blendSpeed, current.blueFloat, target.blueFloat)
            )
            animatable.setCurrentColor(current.color)
        }

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
            current.brighter(1.2).argbInt
        )
    }
}