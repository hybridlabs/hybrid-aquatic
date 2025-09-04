package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TunaEntityModel
import dev.hybridlabs.aquatic.client.renderer.entity.fish.HybridAquaticFishEntityRenderer
import dev.hybridlabs.aquatic.entity.fish.TunaEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TunaEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<TunaEntity>(context, TunaEntityModel(), true, false)