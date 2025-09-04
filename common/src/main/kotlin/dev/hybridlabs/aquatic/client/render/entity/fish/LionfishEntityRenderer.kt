package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.LionfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.LionfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LionfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<LionfishEntity>(context, LionfishEntityModel(), true, false)