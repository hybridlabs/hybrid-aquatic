package dev.hybridlabs.aquatic.client.render

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation


@Suppress("INFERRED_INVISIBLE_RETURN_TYPE_WARNING")
class HARenderTypes(
    name: String,
    format: VertexFormat,
    mode: VertexFormat.Mode,
    bufferSize: Int,
    affectsCrumbling: Boolean,
    sortOnUpload: Boolean,
    setupState: Runnable,
    clearState: Runnable)
    : RenderType(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState) {

    companion object {
        fun getSubmarineLights(texture: ResourceLocation): RenderType {
            return create(
                "submarine_lights",
                DefaultVertexFormat.POSITION_COLOR,
                VertexFormat.Mode.QUADS,
                256,
                true,
                true,
                CompositeState.builder()
                    .setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                    .setTextureState(TextureStateShard(texture, false, false))
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setCullState(CULL)
                    .setDepthTestState(LEQUAL_DEPTH_TEST)
                    .setLightmapState(NO_LIGHTMAP)
                    .setOutputState(ITEM_ENTITY_TARGET)
                    .createCompositeState(false)
            )
        }

        fun getSubmarineMask(texture: ResourceLocation): RenderType {
            return create(
                "submarine_mask",
                DefaultVertexFormat.POSITION,
                VertexFormat.Mode.QUADS,
                256,
                true,
                true,
                CompositeState.builder().setShaderState(RENDERTYPE_WATER_MASK_SHADER).setTextureState(TextureStateShard(texture, false, false))
                    .setDepthTestState(LEQUAL_DEPTH_TEST).setWriteMaskState(DEPTH_WRITE).setCullState(NO_CULL)
                    .createCompositeState(false)
            )
        }

        private val EYES_ALPHA_TRANSPARENCY: TransparencyStateShard =
            TransparencyStateShard("eyes_alpha_transparency", {
                RenderSystem.enableBlend()
                RenderSystem.blendFuncSeparate(
                    GlStateManager.SourceFactor.SRC_ALPHA,
                    GlStateManager.DestFactor.ONE,
                    GlStateManager.SourceFactor.ONE,
                    GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA
                )
            }, {
                RenderSystem.disableBlend()
                RenderSystem.defaultBlendFunc()
            })

        fun getEyesAlphaEnabled(locationIn: ResourceLocation): RenderType {
            val `rendertype$compositestate` = CompositeState.builder().setShaderState(RENDERTYPE_EYES_SHADER)
                .setTextureState(TextureStateShard(locationIn, false, false))
                .setTransparencyState(EYES_ALPHA_TRANSPARENCY).setCullState(NO_CULL).setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY).setDepthTestState(EQUAL_DEPTH_TEST).createCompositeState(true)
            return create(
                "eye_alpha",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true,
                false,
                `rendertype$compositestate`
            )
        }
    }
}
