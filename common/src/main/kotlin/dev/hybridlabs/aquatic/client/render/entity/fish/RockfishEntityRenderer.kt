package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.RockfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.RockfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class RockfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<RockfishEntity>(context, RockfishEntityModel(), true, false)