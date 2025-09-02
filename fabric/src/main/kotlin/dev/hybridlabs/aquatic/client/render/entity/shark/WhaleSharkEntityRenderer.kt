package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.WhaleSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class WhaleSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<HybridAquaticSharkEntity>(context, WhaleSharkEntityModel(), true)