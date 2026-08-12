package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.FireworkJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.FireworkJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FireworkJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<FireworkJellyfishEntity>(context, FireworkJellyfishEntityModel(), true, true)