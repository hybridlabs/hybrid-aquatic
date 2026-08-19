package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SunfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SunfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SunfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SunfishEntity>(context, SunfishEntityModel(), true, false)