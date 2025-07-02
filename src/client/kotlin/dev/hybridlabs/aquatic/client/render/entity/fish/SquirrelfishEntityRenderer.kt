package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SquirrelfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SquirrelfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SquirrelfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SquirrelfishEntity>(context, SquirrelfishEntityModel(), true, false)