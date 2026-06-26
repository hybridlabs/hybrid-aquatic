package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BettaEntityModel
import dev.hybridlabs.aquatic.entity.fish.BettaEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BettaEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<BettaEntity>(context, BettaEntityModel(), false, false)
