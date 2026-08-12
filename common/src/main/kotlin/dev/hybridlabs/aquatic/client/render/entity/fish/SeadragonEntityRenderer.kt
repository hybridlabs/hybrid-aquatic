package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeadragonEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeadragonEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SeadragonEntity>(context, SeadragonEntityModel(), true, false)