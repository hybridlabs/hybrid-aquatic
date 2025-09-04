package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeadragonEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeadragonEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SeadragonEntity>(context, SeadragonEntityModel(), true, false)