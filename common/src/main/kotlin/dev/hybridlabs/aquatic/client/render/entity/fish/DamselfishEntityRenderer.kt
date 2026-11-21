package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DamselfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DamselfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class DamselfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<DamselfishEntity, LivingEntityRenderState>(context, DamselfishEntityModel(), true, false)