package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CarpEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.layer.CarpPatternEntityLayer
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CarpEntityRenderer(context: Context) :
    HAFishEntityRenderer<CarpEntity>(context, CarpEntityModel(), true, false) {

    init {
        addRenderLayer(CarpPatternEntityLayer(this))
    }
}