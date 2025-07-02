package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DamselfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DamselfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DamselfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<DamselfishEntity>(context, DamselfishEntityModel(), true, false)