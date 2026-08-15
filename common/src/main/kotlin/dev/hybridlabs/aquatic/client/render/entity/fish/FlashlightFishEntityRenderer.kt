package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.FlashlightFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FlashlightFishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<FlashlightFishEntity>(context, FlashlightFishEntityModel(), true, true)