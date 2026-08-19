package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DiscusEntityModel
import dev.hybridlabs.aquatic.entity.fish.DiscusEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DiscusEntityRenderer(context: Context) :
    BaseFishEntityRenderer<DiscusEntity>(context, DiscusEntityModel(), true, false)
