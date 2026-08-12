package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CombJellyEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CombJellyEntity
import dev.hybridlabs.hapi.client.render.entity.BaseJellyfishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CombJellyEntityRenderer(context: Context) :
    BaseJellyfishEntityRenderer<CombJellyEntity>(context, CombJellyEntityModel(), true, true)