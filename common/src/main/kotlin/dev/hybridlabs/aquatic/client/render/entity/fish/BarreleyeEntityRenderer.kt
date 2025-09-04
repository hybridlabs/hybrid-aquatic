package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BarreleyeEntityModel
import dev.hybridlabs.aquatic.entity.fish.BarreleyeEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BarreleyeEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<BarreleyeEntity>(context, BarreleyeEntityModel(), true, true)