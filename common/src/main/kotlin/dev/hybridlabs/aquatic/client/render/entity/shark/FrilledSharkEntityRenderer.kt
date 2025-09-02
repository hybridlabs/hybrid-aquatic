package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.FrilledSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FrilledSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<HybridAquaticSharkEntity>(context, FrilledSharkEntityModel(), true)