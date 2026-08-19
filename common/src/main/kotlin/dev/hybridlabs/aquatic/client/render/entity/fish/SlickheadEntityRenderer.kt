package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SlickheadEntityModel
import dev.hybridlabs.aquatic.entity.fish.SlickheadEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SlickheadEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SlickheadEntity>(context, SlickheadEntityModel(), true, false)