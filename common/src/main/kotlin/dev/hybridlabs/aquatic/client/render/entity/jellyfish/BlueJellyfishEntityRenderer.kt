package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BlueJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BlueJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BlueJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<BlueJellyfishEntity>(context, BlueJellyfishEntityModel(), true, false)