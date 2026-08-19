package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CosmicJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CosmicJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CosmicJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<CosmicJellyfishEntity>(context, CosmicJellyfishEntityModel(), true, false)