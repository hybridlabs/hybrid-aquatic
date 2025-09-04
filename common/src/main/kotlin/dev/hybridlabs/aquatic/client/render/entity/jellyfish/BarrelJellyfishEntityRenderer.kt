package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BarrelJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BarrelJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BarrelJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<BarrelJellyfishEntity>(context, BarrelJellyfishEntityModel(), true, false)