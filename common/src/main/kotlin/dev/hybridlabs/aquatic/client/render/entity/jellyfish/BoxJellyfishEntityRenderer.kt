package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BoxJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BoxJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(context, BoxJellyfishEntityModel(), true, false)