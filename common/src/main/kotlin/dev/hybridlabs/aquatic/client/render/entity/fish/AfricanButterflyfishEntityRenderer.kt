package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.AfricanButterflyfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.AfricanButterflyfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class AfricanButterflyfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<AfricanButterflyfishEntity>(context, AfricanButterflyfishEntityModel(), false, false)
