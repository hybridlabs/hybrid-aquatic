package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.OpahEntityModel
import dev.hybridlabs.aquatic.entity.fish.OpahEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OpahEntityRenderer(context: Context) :
    BaseFishEntityRenderer<OpahEntity>(context, OpahEntityModel(), true, false)