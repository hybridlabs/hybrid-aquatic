package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BoxfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.BoxfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BoxfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<BoxfishEntity>(context, BoxfishEntityModel(), false, false)
