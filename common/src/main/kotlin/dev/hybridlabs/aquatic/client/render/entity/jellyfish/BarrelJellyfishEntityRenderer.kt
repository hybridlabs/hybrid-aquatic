package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BarrelJellyfishEntityModel
import dev.hybridlabs.aquatic.client.renderer.entity.jellyfish.HybridAquaticJellyfishEntityRenderer
import dev.hybridlabs.aquatic.entity.jellyfish.BarrelJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BarrelJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<BarrelJellyfishEntity>(context, BarrelJellyfishEntityModel(), true, false)