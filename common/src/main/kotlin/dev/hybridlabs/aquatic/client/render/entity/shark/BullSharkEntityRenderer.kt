package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.BullSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BullSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<HybridAquaticSharkEntity>(context, BullSharkEntityModel(), true)