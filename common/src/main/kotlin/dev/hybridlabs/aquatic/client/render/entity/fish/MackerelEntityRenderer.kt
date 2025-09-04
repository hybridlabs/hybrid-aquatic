package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MackerelEntityModel
import dev.hybridlabs.aquatic.entity.fish.MackerelEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MackerelEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<MackerelEntity>(context, MackerelEntityModel(), true, false)