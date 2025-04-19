package dev.hybridlabs.aquatic.client.render.entity.turtle

import dev.hybridlabs.aquatic.client.model.entity.turtle.GreenTurtleEntityModel
import dev.hybridlabs.aquatic.entity.turtle.HybridAquaticTurtleEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GreenTurtleEntityRenderer(context: Context) :
    HybridAquaticTurtleEntityRenderer<HybridAquaticTurtleEntity>(context, GreenTurtleEntityModel())
