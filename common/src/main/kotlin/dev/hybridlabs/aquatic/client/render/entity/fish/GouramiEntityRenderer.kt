package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GouramiEntityModel
import dev.hybridlabs.aquatic.entity.fish.GouramiEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GouramiEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<GouramiEntity>(context, GouramiEntityModel(), true, false)
