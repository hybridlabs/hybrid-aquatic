package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MantaRayEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MantaRayEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<HybridAquaticFishEntity>(context, MantaRayEntityModel(), true, false)