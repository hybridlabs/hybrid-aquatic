package dev.hybridlabs.aquatic.client.render.entity.critter

import net.minecraft.resources.Identifier
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.rendertype.RenderType
import dev.hybridlabs.aquatic.client.model.entity.critter.SeaCucumberEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SeaCucumberEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<SeaCucumberEntity, LivingEntityRenderState>(context, SeaCucumberEntityModel(), true) {

    override fun getRenderType(
        renderState: LivingEntityRenderState,
        texture: Identifier
    ): RenderType {
        return RenderTypes.entityTranslucent(texture)
    }
}
