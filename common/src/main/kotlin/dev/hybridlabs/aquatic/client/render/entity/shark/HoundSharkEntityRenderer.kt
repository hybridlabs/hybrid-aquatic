package dev.hybridlabs.aquatic.client.renderer.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.HoundSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HoundSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<HoundSharkEntity>(context, HoundSharkEntityModel(), true)