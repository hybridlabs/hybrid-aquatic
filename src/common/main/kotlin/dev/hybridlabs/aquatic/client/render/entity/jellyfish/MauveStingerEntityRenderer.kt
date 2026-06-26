package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.MauveStingerEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.MauveStingerEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MauveStingerEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<MauveStingerEntity>(context, MauveStingerEntityModel(), true, false)