package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.LionfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.LionfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class LionfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<LionfishEntity>(context, LionfishEntityModel(), true, false)