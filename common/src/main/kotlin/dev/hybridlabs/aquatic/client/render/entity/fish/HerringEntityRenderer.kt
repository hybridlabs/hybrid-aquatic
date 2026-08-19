package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.HerringEntityModel
import dev.hybridlabs.aquatic.entity.fish.HerringEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HerringEntityRenderer(context: Context) :
    BaseFishEntityRenderer<HerringEntity>(context, HerringEntityModel(), true, false)