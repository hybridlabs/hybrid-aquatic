package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SquirrelfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SquirrelfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SquirrelfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SquirrelfishEntity>(context, SquirrelfishEntityModel(), true, false)