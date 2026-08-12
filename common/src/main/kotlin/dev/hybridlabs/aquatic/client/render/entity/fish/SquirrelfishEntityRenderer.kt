package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SquirrelfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SquirrelfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SquirrelfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SquirrelfishEntity>(context, SquirrelfishEntityModel(), true, false)