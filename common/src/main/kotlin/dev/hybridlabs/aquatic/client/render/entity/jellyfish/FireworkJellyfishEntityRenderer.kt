package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.FireworkJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FireworkJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(
        context,
        FireworkJellyfishEntityModel(),
        true,
        true
    )