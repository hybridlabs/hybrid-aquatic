package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.StingrayEntityModel
import dev.hybridlabs.aquatic.entity.fish.StingrayEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class StingrayEntityRenderer(context: Context) :
    BaseFishEntityRenderer<StingrayEntity, LivingEntityRenderState>(context, StingrayEntityModel(), true, false)