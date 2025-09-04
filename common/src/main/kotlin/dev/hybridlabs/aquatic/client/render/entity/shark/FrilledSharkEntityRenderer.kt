package dev.hybridlabs.aquatic.client.renderer.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.FrilledSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.FrilledSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FrilledSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<FrilledSharkEntity>(context, FrilledSharkEntityModel(), true)