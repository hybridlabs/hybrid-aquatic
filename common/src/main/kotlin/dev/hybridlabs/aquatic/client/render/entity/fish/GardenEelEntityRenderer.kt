package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GardenEelEntityModel
import dev.hybridlabs.aquatic.entity.fish.GardenEelEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GardenEelEntityRenderer(context: Context) :
    HAFishEntityRenderer<GardenEelEntity>(context, GardenEelEntityModel(), true, false)
