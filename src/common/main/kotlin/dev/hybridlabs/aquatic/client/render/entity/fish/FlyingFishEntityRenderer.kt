package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.FlyingFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.FlyingFishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FlyingFishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<FlyingFishEntity>(context, FlyingFishEntityModel(), false, false)
