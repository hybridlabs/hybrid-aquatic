package dev.hybridlabs.aquatic.client.renderer.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.KillerWhaleEntityModel
import dev.hybridlabs.aquatic.entity.mammal.KillerWhaleEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class KillerWhaleEntityRenderer(context: Context) :
    HybridAquaticDolphinEntityRenderer<KillerWhaleEntity>(context, KillerWhaleEntityModel())
