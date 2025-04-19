package dev.hybridlabs.aquatic.client.render.entity.turtle

import dev.hybridlabs.aquatic.client.model.entity.turtle.RidleyTurtleEntityModel
import dev.hybridlabs.aquatic.entity.turtle.HybridAquaticTurtleEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class RidleyTurtleEntityRenderer(context: Context) :
    HybridAquaticTurtleEntityRenderer<HybridAquaticTurtleEntity>(context, RidleyTurtleEntityModel())
