package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import net.minecraft.resources.Identifier
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.rendertype.RenderType
import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BlueJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BlueJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class BlueJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<BlueJellyfishEntity, LivingEntityRenderState>(context, BlueJellyfishEntityModel(), true, false) {

    override fun getRenderType(
        renderState: LivingEntityRenderState,
        texture: Identifier
    ): RenderType {
        return RenderTypes.entityTranslucent(texture)
    }
}
