package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GoldfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.GoldfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GoldfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<GoldfishEntity>(context, GoldfishEntityModel(), true, false)
