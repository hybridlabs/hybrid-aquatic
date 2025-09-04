package dev.hybridlabs.aquatic.client.renderer.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.SeaNettleEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.SeaNettleEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaNettleEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<SeaNettleEntity>(context, SeaNettleEntityModel(), true, false)