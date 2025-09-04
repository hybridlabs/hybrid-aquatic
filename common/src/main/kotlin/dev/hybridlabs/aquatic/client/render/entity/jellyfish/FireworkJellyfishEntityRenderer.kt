package dev.hybridlabs.aquatic.client.renderer.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.FireworkJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.FireworkJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FireworkJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<FireworkJellyfishEntity>(context, FireworkJellyfishEntityModel(), true, true)