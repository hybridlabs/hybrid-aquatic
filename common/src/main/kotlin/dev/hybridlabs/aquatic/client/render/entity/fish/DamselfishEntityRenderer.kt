package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DamselfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DamselfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DamselfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<DamselfishEntity>(context, DamselfishEntityModel(), true, false)