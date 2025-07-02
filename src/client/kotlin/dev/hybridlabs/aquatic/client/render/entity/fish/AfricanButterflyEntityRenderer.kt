package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.AfricanButterflyEntityModel
import dev.hybridlabs.aquatic.entity.fish.AfricanButterflyEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class AfricanButterflyEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<AfricanButterflyEntity>(context, AfricanButterflyEntityModel(), false, false)
