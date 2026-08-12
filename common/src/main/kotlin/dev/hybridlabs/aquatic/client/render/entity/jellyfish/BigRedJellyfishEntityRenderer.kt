package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.BigRedJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.BigRedJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BigRedJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<BigRedJellyfishEntity>(context, BigRedJellyfishEntityModel(), true, false)