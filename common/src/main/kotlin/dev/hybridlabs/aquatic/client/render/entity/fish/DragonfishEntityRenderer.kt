package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DragonfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DragonfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DragonfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<DragonfishEntity>(context, DragonfishEntityModel(), true, true)