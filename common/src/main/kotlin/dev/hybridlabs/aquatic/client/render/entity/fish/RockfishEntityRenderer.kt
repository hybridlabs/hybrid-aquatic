package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.RockfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.RockfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class RockfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<RockfishEntity>(context, RockfishEntityModel(), true, false)