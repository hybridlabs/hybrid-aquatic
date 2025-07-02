package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CompassJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CompassJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CompassJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<CompassJellyfishEntity>(context, CompassJellyfishEntityModel(), true, false)