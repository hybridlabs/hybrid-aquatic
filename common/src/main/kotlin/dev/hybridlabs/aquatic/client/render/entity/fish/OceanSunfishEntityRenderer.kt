package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.OceanSunfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.OceanSunfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OceanSunfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<OceanSunfishEntity>(context, OceanSunfishEntityModel(), true, false)