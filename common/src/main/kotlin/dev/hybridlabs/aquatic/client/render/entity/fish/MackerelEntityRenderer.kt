package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MackerelEntityModel
import dev.hybridlabs.aquatic.entity.fish.MackerelEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MackerelEntityRenderer(context: Context) :
    BaseFishEntityRenderer<MackerelEntity>(context, MackerelEntityModel(), true, false)