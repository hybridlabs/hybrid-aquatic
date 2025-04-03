package dev.hybridlabs.aquatic.client.render.entity.turtle

import dev.hybridlabs.aquatic.client.model.entity.turtle.LeatherbackTurtleEntityModel
import dev.hybridlabs.aquatic.entity.turtle.HybridAquaticTurtleEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class LeatherbackTurtleEntityRenderer(context: Context) :
    HybridAquaticTurtleEntityRenderer<HybridAquaticTurtleEntity>(context, LeatherbackTurtleEntityModel())
