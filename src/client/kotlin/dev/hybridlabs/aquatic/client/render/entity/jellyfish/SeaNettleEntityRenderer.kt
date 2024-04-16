package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.SeaNettleEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SeaNettleEntityRenderer(context: Context) : HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(context, SeaNettleEntityModel(), true, false)