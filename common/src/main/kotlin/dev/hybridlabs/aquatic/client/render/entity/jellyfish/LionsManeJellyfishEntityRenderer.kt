package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.LionsManeJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.LionsManeJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LionsManeJellyfishEntityRenderer(context: Context) :
    HAJellyfishEntityRenderer<LionsManeJellyfishEntity>(context, LionsManeJellyfishEntityModel(), true, false)