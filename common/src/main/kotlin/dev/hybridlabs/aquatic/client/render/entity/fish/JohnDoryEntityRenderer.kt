package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.JohnDoryEntityModel
import dev.hybridlabs.aquatic.entity.fish.JohnDoryEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class JohnDoryEntityRenderer(context: Context) :
    BaseFishEntityRenderer<JohnDoryEntity>(context, JohnDoryEntityModel(), true, false)
