package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.SixgillSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.SixgillSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SixgillSharkEntityRenderer(context: Context) :
    HybridAquaticSharkEntityRenderer<SixgillSharkEntity>(context, SixgillSharkEntityModel(), true)