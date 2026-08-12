package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GoldfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.GoldfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GoldfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<GoldfishEntity>(context, GoldfishEntityModel(), true, false)