package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BarreleyeEntityModel
import dev.hybridlabs.aquatic.entity.fish.BarreleyeEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BarreleyeEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<BarreleyeEntity>(context, BarreleyeEntityModel(), true, true)