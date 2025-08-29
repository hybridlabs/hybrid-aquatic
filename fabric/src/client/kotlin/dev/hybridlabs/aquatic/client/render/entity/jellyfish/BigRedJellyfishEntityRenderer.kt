package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BigRedJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BigRedJellyfishEntityRenderer(context: Context) : HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(context, BigRedJellyfishEntityModel(), true, false)