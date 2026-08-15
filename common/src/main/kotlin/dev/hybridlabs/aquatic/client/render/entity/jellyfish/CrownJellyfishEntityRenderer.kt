package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CrownJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CrownJellyfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CrownJellyfishEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<CrownJellyfishEntity>(context, CrownJellyfishEntityModel(), true, true)