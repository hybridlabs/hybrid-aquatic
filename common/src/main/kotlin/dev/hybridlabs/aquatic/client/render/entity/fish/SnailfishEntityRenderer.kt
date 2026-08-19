package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SnailfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SnailfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SnailfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SnailfishEntity>(context, SnailfishEntityModel(), true, false)
