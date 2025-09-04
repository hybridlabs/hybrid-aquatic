package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.HoundSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class HoundSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<HoundSharkEntity>(context, HoundSharkEntityModel(), true)