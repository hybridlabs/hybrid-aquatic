package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CrownJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CrownJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CrownJellyfishEntityRenderer(context: Context) :
    HAJellyfishEntityRenderer<CrownJellyfishEntity>(context, CrownJellyfishEntityModel(), true, true)