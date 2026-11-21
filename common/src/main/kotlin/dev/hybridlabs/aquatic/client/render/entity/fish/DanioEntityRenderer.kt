package dev.hybridlabs.aquatic.client.render.entity.fish

import net.minecraft.resources.Identifier
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.rendertype.RenderType
import dev.hybridlabs.aquatic.client.model.entity.fish.DanioEntityModel
import dev.hybridlabs.aquatic.entity.fish.DanioEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class DanioEntityRenderer(context: Context)
    : BaseFishEntityRenderer<DanioEntity, LivingEntityRenderState>(context, DanioEntityModel(), false, false) {

    override fun getRenderType(
        renderState: LivingEntityRenderState,
        texture: Identifier
    ): RenderType {
        return RenderTypes.entityTranslucent(texture)
    }
}
