package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import net.minecraft.resources.Identifier
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.rendertype.RenderType
import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CosmicJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CosmicJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class CosmicJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<CosmicJellyfishEntity, LivingEntityRenderState>(context, CosmicJellyfishEntityModel(), true, false) {

    override fun getRenderType(
        renderState: LivingEntityRenderState,
        texture: Identifier
    ): RenderType {
        return RenderTypes.entityTranslucent(texture)
    }
}
