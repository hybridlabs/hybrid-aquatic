package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BoxJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BoxJellyfishEntityRenderer(context: Context) : HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(context, BoxJellyfishEntityModel(), true, false)