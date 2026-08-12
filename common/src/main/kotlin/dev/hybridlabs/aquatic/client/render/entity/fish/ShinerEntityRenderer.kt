package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ShinerEntityModel
import dev.hybridlabs.aquatic.entity.fish.ShinerEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ShinerEntityRenderer(context: Context) :
    BaseFishEntityRenderer<ShinerEntity>(context, ShinerEntityModel(), true, false)
