package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import net.minecraft.resources.Identifier
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.rendertype.RenderType
import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BigRedJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BigRedJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class BigRedJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<BigRedJellyfishEntity, LivingEntityRenderState>(context, BigRedJellyfishEntityModel(), true, false) {

    override fun getRenderType(
        renderState: LivingEntityRenderState,
        texture: Identifier
    ): RenderType {
        return RenderTypes.entityTranslucent(texture)
    }
}
