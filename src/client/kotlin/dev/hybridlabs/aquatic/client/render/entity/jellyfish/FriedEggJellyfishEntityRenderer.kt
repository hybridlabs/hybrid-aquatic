package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.FriedEggJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.FriedEggJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class FriedEggJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<FriedEggJellyfishEntity>(context, FriedEggJellyfishEntityModel(), true, false)