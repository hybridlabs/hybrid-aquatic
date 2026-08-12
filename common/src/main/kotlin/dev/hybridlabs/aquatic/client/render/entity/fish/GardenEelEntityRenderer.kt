package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GardenEelEntityModel
import dev.hybridlabs.aquatic.entity.fish.GardenEelEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GardenEelEntityRenderer(context: Context) :
    BaseFishEntityRenderer<GardenEelEntity>(context, GardenEelEntityModel(), true, false)
