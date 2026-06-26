package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeahorseEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeahorseEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeahorseEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SeahorseEntity>(context, SeahorseEntityModel(), true, false)