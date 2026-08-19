package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.NeedlefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.NeedlefishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class NeedlefishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<NeedlefishEntity>(context, NeedlefishEntityModel(), true, false)