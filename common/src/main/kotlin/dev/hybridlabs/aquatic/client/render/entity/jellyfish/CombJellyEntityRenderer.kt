package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CombJellyEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CombJellyEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CombJellyEntityRenderer(context: Context) :
    HAJellyfishEntityRenderer<CombJellyEntity>(context, CombJellyEntityModel(), true, true)