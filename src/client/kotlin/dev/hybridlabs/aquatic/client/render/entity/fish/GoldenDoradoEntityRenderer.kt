package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GoldenDoradoEntityModel
import dev.hybridlabs.aquatic.entity.fish.GoldenDoradoEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GoldenDoradoEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<GoldenDoradoEntity>(context, GoldenDoradoEntityModel(), true, false)