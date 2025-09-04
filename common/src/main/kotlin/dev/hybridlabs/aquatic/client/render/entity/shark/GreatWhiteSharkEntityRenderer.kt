package dev.hybridlabs.aquatic.client.renderer.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.GreatWhiteSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.GreatWhiteSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GreatWhiteSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<GreatWhiteSharkEntity>(context, GreatWhiteSharkEntityModel(), true, false)