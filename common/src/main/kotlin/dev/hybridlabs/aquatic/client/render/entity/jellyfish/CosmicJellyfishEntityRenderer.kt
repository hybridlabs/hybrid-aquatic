package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CosmicJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CosmicJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CosmicJellyfishEntityRenderer(context: Context) :
    HAJellyfishEntityRenderer<CosmicJellyfishEntity>(context, CosmicJellyfishEntityModel(), true, false)