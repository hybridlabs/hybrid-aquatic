package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MantaRayEntityModel
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class MantaRayEntityRenderer(context: Context) : HybridAquaticRayEntityRenderer<HybridAquaticRayEntity>(context, MantaRayEntityModel(), true, false)