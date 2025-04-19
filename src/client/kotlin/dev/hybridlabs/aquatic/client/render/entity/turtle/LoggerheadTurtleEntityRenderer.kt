package dev.hybridlabs.aquatic.client.render.entity.turtle

import dev.hybridlabs.aquatic.client.model.entity.turtle.LoggerheadTurtleEntityModel
import dev.hybridlabs.aquatic.entity.turtle.HybridAquaticTurtleEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class LoggerheadTurtleEntityRenderer(context: Context) :
    HybridAquaticTurtleEntityRenderer<HybridAquaticTurtleEntity>(context, LoggerheadTurtleEntityModel())
