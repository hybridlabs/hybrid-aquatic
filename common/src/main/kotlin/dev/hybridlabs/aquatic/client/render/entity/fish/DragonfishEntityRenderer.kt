package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DragonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DragonfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class DragonfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<DragonfishEntity, LivingEntityRenderState>(context, DragonfishEntityModel(), true, true)