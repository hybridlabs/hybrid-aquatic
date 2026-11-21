package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GardenEelEntityModel
import dev.hybridlabs.aquatic.entity.fish.GardenEelEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class GardenEelEntityRenderer(context: Context) :
    BaseFishEntityRenderer<GardenEelEntity, LivingEntityRenderState>(context, GardenEelEntityModel(), true, false)
