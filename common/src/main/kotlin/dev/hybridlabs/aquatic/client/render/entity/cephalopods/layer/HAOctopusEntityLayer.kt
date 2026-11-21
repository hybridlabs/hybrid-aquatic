package dev.hybridlabs.aquatic.client.render.entity.cephalopods.layer

import dev.hybridlabs.hapi.client.model.getVariant

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.entity.cephalopod.OctopusEntity
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseOctopusEntityModel
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseOctopusEntityRenderer
import dev.hybridlabs.hapi.entity.base.aquatic.BaseOctopusEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth

class HAOctopusEntityLayer<T : BaseOctopusEntity>(
    renderer: BaseOctopusEntityRenderer<T, LivingEntityRenderState>,
) : HATextureLayer<T>(renderer, tinted = true) {

    override fun addRenderData(
        animatable: T,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)

        val overlayName = (animatable as? OverlayTextureFeature)?.getOverlayTextureName() ?: ""

        renderState.addGeckolibData(
            HADataTickets.OVERLAY_NAME,
            if (animatable.isSitting()) overlayName else ""
        )

        var current = animatable.getCurrentColor()
        val target = animatable.getTargetColor()

        if (current != target) {
            val blendSpeed = 0.025f
            current = (0xFF shl 24) or
                (Mth.lerp(blendSpeed, red(current), red(target)) * 255f).toInt().shl(16) or
                (Mth.lerp(blendSpeed, green(current), green(target)) * 255f).toInt().shl(8) or
                (Mth.lerp(blendSpeed, blue(current), blue(target)) * 255f).toInt()
            animatable.setCurrentColor(current)
        }

        renderState.addGeckolibData(HADataTickets.CURRENT_COLOR, brighten(current))
    }

    override fun layerTexture(renderState: LivingEntityRenderState): Identifier? {
        val overlayName = renderState.getGeckolibData(HADataTickets.OVERLAY_NAME) ?: return null

        if (overlayName.isEmpty()) return null

        if (renderState.getVariant(OctopusEntity.Companion.Type.entries) != OctopusEntity.Companion.Type.OCTOPUS) {
            return null
        }

        return (getGeoModel() as BaseOctopusEntityModel).getLayerTextureResource()
    }

    override fun layerTint(renderState: LivingEntityRenderState): Int =
        renderState.getGeckolibData(HADataTickets.CURRENT_COLOR) ?: WHITE

    private fun red(color: Int) = ((color shr 16) and 0xFF) / 255f

    private fun green(color: Int) = ((color shr 8) and 0xFF) / 255f

    private fun blue(color: Int) = (color and 0xFF) / 255f

    private fun brighten(color: Int): Int {
        val r = (((color shr 16) and 0xFF) * 1.2f).toInt().coerceAtMost(255)
        val g = (((color shr 8) and 0xFF) * 1.2f).toInt().coerceAtMost(255)
        val b = ((color and 0xFF) * 1.2f).toInt().coerceAtMost(255)

        return (0xFF shl 24) or (r shl 16) or (g shl 8) or b
    }
}
