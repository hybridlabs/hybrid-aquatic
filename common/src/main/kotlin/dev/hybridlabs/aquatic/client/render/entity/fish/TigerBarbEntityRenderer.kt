package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TigerBarbEntityModel
import dev.hybridlabs.aquatic.entity.fish.TigerBarbEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class TigerBarbEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TigerBarbEntity, LivingEntityRenderState>(context, TigerBarbEntityModel(), true, false)