package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BoxJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BoxJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BoxJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<BoxJellyfishEntity>(context, BoxJellyfishEntityModel(), true, false)