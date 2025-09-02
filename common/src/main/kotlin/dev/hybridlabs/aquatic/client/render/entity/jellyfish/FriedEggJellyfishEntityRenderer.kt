package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.FriedEggJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FriedEggJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(
        context,
        FriedEggJellyfishEntityModel(),
        true,
        false
    )