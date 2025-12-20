package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TroutEntityModel
import dev.hybridlabs.aquatic.entity.fish.TroutEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TroutEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<TroutEntity>(context, TroutEntityModel(), true, false)