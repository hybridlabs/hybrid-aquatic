package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.ThresherSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ThresherSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<HybridAquaticSharkEntity>(context, ThresherSharkEntityModel(), true)