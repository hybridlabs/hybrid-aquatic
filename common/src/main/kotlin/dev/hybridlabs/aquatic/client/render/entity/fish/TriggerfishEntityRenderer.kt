package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TriggerfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.TriggerfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class TriggerfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TriggerfishEntity, LivingEntityRenderState>(context, TriggerfishEntityModel(), true, false)