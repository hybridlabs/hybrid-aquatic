package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.LionsManeJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.LionsManeJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LionsManeJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<LionsManeJellyfishEntity>(context, LionsManeJellyfishEntityModel(), true, false)