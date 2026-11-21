package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CarpEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.layer.CarpPatternEntityLayer
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class CarpEntityRenderer(context: Context) :
    BaseFishEntityRenderer<CarpEntity, LivingEntityRenderState>(context, CarpEntityModel(), true, false) {

    init {
        withRenderLayer(CarpPatternEntityLayer(this))
    }
}