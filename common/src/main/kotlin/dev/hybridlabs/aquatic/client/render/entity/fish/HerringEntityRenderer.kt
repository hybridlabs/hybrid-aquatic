package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.HerringEntityModel
import dev.hybridlabs.aquatic.entity.fish.HerringEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class HerringEntityRenderer(context: Context) :
    BaseFishEntityRenderer<HerringEntity, LivingEntityRenderState>(context, HerringEntityModel(), true, false)