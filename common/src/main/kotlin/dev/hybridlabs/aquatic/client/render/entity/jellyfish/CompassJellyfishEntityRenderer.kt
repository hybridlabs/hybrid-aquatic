package dev.hybridlabs.aquatic.client.renderer.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CompassJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CompassJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(
        context,
        CompassJellyfishEntityModel(),
        true,
        false
    )