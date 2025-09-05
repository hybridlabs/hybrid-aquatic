package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BigRedJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BigRedJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BigRedJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<BigRedJellyfishEntity>(context, BigRedJellyfishEntityModel(), true, false)