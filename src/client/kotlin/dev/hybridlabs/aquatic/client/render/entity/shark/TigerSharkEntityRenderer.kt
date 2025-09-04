package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.TigerSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.TigerSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class TigerSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<TigerSharkEntity>(context, TigerSharkEntityModel(), true)