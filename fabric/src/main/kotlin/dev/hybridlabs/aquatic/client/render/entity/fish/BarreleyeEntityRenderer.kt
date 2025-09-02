package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BarreleyeEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BarreleyeEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<HybridAquaticFishEntity>(context, BarreleyeEntityModel(), true, true)