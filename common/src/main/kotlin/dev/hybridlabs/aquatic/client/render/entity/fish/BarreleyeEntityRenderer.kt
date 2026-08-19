package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BarreleyeEntityModel
import dev.hybridlabs.aquatic.entity.fish.BarreleyeEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BarreleyeEntityRenderer(context: Context) :
    BaseFishEntityRenderer<BarreleyeEntity>(context, BarreleyeEntityModel(), true, true)