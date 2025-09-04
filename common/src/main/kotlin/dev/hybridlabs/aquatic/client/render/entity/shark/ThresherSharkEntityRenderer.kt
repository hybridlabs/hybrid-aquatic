package dev.hybridlabs.aquatic.client.renderer.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.ThresherSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.ThresherSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ThresherSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<ThresherSharkEntity>(context, ThresherSharkEntityModel(), true)