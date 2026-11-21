package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TroutEntityModel
import dev.hybridlabs.aquatic.entity.fish.TroutEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class TroutEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TroutEntity, LivingEntityRenderState>(context, TroutEntityModel(), true, false)