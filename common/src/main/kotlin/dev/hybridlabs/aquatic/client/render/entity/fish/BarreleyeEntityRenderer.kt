package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BarreleyeEntityModel
import dev.hybridlabs.aquatic.entity.fish.BarreleyeEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class BarreleyeEntityRenderer(context: Context) :
    BaseFishEntityRenderer<BarreleyeEntity, LivingEntityRenderState>(context, BarreleyeEntityModel(), true, true)