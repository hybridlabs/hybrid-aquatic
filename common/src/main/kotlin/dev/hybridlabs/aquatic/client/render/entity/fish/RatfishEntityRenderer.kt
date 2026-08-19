package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.RatfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.RatfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class RatfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<RatfishEntity>(context, RatfishEntityModel(), true, false)