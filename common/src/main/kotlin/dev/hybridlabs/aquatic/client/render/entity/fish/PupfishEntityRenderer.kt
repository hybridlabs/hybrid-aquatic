package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PupfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.PupfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PupfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<PupfishEntity>(context, PupfishEntityModel(), false, false)
