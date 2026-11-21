package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import net.minecraft.resources.Identifier
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.rendertype.RenderType
import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CepheidaeJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CepheidaeJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class CepheidaeJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<CepheidaeJellyfishEntity, LivingEntityRenderState>(context, CepheidaeJellyfishEntityModel(), true, false) {

    override fun getRenderType(
        renderState: LivingEntityRenderState,
        texture: Identifier
    ): RenderType {
        return RenderTypes.entityTranslucent(texture)
    }
}
