package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PearlfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.PearlfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PearlfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<PearlfishEntity>(context, PearlfishEntityModel(), true, false)