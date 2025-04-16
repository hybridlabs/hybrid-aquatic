package dev.hybridlabs.aquatic.client.render.entity.turtle

import dev.hybridlabs.aquatic.client.model.entity.turtle.HawksbillTurtleEntityModel
import dev.hybridlabs.aquatic.entity.turtle.HybridAquaticTurtleEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class HawksbillTurtleEntityRenderer(context: Context) :
    HybridAquaticTurtleEntityRenderer<HybridAquaticTurtleEntity>(context, HawksbillTurtleEntityModel())
