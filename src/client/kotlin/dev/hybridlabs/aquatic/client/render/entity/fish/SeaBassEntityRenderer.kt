package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeaBassEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeaBassEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SeaBassEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SeaBassEntity>(context, SeaBassEntityModel(), true, false)