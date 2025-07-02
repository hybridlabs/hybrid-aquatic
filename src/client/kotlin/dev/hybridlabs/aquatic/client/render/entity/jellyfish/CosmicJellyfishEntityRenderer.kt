package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CosmicJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CosmicJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CosmicJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<CosmicJellyfishEntity>(context, CosmicJellyfishEntityModel(), true, false)