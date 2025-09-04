package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.LanternSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.LanternSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LanternSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<LanternSharkEntity>(context, LanternSharkEntityModel(), true, true)