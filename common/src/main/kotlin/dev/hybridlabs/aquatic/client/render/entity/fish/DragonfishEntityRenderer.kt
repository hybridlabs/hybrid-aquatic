package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DragonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DragonfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DragonfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<DragonfishEntity>(context, DragonfishEntityModel(), true, true)