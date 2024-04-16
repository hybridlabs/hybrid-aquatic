package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.AtollaJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class AtollaJellyfishEntityRenderer(context: Context) : HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(context, AtollaJellyfishEntityModel(), true, true)