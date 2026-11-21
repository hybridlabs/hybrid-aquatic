package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.StonefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.StonefishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class StonefishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<StonefishEntity, LivingEntityRenderState>(context, StonefishEntityModel(), true, false)

