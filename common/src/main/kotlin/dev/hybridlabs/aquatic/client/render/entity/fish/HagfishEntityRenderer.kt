package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.HagfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HagfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HagfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<HagfishEntity>(context, HagfishEntityModel(), true, false)