package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ParrotfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ParrotfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ParrotfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<ParrotfishEntity>(context, ParrotfishEntityModel(), true, false)