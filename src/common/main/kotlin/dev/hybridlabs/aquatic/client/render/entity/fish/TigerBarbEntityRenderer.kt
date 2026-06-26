package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TigerBarbEntityModel
import dev.hybridlabs.aquatic.entity.fish.TigerBarbEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TigerBarbEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<TigerBarbEntity>(context, TigerBarbEntityModel(), true, false)