package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.BullSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.BullSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BullSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<BullSharkEntity>(context, BullSharkEntityModel(), true)