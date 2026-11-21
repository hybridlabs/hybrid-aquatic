package dev.hybridlabs.aquatic.client.render.entity.layer

import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.renderer.base.GeoRenderer
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.TextureLayerGeoLayer

/**
 * Base class for Hybrid Aquatic's extra texture passes. GeckoLib 5 layers only see the render state,
 * so subclasses pick their texture from captured state and return null when they should not draw.
 */
abstract class HATextureLayer<T : GeoAnimatable>(
    renderer: GeoRenderer<T, Void, LivingEntityRenderState>,
    private val tinted: Boolean = false,
) : TextureLayerGeoLayer<T, Void, LivingEntityRenderState>(renderer, PLACEHOLDER_TEXTURE) {

    /** The texture to draw for this pass, or null if the pass should be skipped. */
    protected abstract fun layerTexture(renderState: LivingEntityRenderState): Identifier?

    /** The ARGB tint applied to this pass. */
    protected open fun layerTint(renderState: LivingEntityRenderState): Int = WHITE

    override fun getTextureResource(renderState: LivingEntityRenderState): Identifier =
        layerTexture(renderState) ?: PLACEHOLDER_TEXTURE

    override fun getRenderType(renderState: LivingEntityRenderState): RenderType? {
        val texture = layerTexture(renderState) ?: return null

        return RenderTypes.entityTranslucent(texture)
    }

    override fun submitRenderTask(
        renderPassInfo: RenderPassInfo<LivingEntityRenderState>,
        renderTasks: SubmitNodeCollector
    ) {
        if (!tinted) {
            super.submitRenderTask(renderPassInfo, renderTasks)

            return
        }

        val previous = renderPassInfo.getGeckolibData(DataTickets.RENDER_COLOR)
        renderPassInfo.renderState().addGeckolibData(DataTickets.RENDER_COLOR, layerTint(renderPassInfo.renderState()))
        super.submitRenderTask(renderPassInfo, renderTasks)
        renderPassInfo.renderState().addGeckolibData(DataTickets.RENDER_COLOR, previous ?: WHITE)
    }

    companion object {
        const val WHITE: Int = -1

        private val PLACEHOLDER_TEXTURE = Identifier.withDefaultNamespace("textures/misc/white.png")
    }
}
