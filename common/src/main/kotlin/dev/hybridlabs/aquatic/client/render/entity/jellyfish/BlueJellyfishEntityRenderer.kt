package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BlueJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BlueJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BlueJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<BlueJellyfishEntity>(context, BlueJellyfishEntityModel(), true, false)