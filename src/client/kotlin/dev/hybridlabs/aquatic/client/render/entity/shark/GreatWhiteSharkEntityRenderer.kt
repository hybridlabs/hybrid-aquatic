package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.GreatWhiteSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GreatWhiteSharkEntityRenderer(context: Context) : HybridAquaticSharkEntityRenderer<HybridAquaticSharkEntity>(context, GreatWhiteSharkEntityModel(), true)