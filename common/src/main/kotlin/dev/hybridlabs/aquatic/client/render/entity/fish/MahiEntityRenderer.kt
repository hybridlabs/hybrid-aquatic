package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MahiEntityModel
import dev.hybridlabs.aquatic.entity.fish.MahiEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MahiEntityRenderer(context: Context) :
    BaseFishEntityRenderer<MahiEntity>(context, MahiEntityModel(), true, false)