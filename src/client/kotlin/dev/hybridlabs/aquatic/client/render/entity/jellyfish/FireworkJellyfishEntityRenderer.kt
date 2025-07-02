package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.FireworkJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.FireworkJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class FireworkJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<FireworkJellyfishEntity>(context, FireworkJellyfishEntityModel(), true, true)