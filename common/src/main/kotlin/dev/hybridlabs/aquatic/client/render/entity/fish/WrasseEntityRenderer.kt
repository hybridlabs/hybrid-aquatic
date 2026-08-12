package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.WrasseEntityModel
import dev.hybridlabs.aquatic.entity.fish.WrasseEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class WrasseEntityRenderer(context: Context) :
    BaseFishEntityRenderer<WrasseEntity>(context, WrasseEntityModel(), true, false)