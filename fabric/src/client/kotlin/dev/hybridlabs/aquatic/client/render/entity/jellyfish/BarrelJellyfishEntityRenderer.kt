package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BarrelJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BarrelJellyfishEntityRenderer(context: Context) : HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(context, BarrelJellyfishEntityModel(), true, false)