package dev.hybridlabs.aquatic.client.renderer.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.AtollaJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.AtollaJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class AtollaJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<AtollaJellyfishEntity>(context, AtollaJellyfishEntityModel(), true, true)