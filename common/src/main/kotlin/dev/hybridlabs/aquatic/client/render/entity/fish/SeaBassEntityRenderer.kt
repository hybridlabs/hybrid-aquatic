package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeaBassEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeaBassEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaBassEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SeaBassEntity>(context, SeaBassEntityModel(), true, false)