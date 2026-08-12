package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.OceanSunfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.OceanSunfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OceanSunfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<OceanSunfishEntity>(context, OceanSunfishEntityModel(), true, false)