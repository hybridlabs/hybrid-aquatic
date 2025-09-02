package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.NomuraJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class NomuraJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<HybridAquaticJellyfishEntity>(
        context,
        NomuraJellyfishEntityModel(),
        true,
        false
    )