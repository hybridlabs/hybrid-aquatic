package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ShinerEntityModel
import dev.hybridlabs.aquatic.entity.fish.ShinerEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ShinerEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<ShinerEntity>(context, ShinerEntityModel(), true, false)
