package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DiscusEntityModel
import dev.hybridlabs.aquatic.entity.fish.DiscusEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DiscusEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<DiscusEntity>(context, DiscusEntityModel(), true, false)
