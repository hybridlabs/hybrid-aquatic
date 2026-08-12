package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeaBassEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeaBassEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaBassEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SeaBassEntity>(context, SeaBassEntityModel(), true, false)