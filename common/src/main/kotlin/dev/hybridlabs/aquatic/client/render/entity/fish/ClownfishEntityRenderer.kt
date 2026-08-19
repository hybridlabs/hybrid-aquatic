package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ClownfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ClownfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<ClownfishEntity>(context, ClownfishEntityModel(), true, false)