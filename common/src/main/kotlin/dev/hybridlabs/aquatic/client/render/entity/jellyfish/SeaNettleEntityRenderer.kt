package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.SeaNettleEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.SeaNettleEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaNettleEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<SeaNettleEntity>(context, SeaNettleEntityModel(), true, false)