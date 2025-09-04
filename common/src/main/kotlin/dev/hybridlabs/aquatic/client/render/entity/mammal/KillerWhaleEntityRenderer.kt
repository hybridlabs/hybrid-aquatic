package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.KillerWhaleEntityModel
import dev.hybridlabs.aquatic.entity.mammal.KillerWhaleEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class KillerWhaleEntityRenderer(context: Context) :
    HybridAquaticDolphinEntityRenderer<KillerWhaleEntity>(context, KillerWhaleEntityModel())
