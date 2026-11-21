package dev.hybridlabs.aquatic.client.render.entity.misc.layer

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.render.entity.layer.HATextureLayer
import dev.hybridlabs.aquatic.client.render.entity.misc.ArgonautEntityRenderer
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier

class ArgonautShellEntityLayer<T : ArgonautEntity>(
    renderer: ArgonautEntityRenderer<T>
) : HATextureLayer<T>(renderer) {

    override fun addRenderData(
        animatable: T,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        renderState.addGeckolibData(HADataTickets.SHELL_COLOR_NAME, animatable.getShellColor().serializedName)
    }

    override fun layerTexture(renderState: LivingEntityRenderState): Identifier? {
        val colorName = renderState.getGeckolibData(HADataTickets.SHELL_COLOR_NAME) ?: return null

        if (colorName == ArgonautEntity.ShellColor.NONE.serializedName) return null

        return CommonClass.locate("textures/entity/misc/argonaut/shell/${colorName}_argonaut_shell.png")
    }
}
