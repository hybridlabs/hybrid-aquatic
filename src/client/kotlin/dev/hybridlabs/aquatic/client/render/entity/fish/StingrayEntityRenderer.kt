package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.StingrayEntityModel
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class StingrayEntityRenderer(context: Context) : HybridAquaticRayEntityRenderer<HybridAquaticRayEntity>(context,
    StingrayEntityModel(), true, false)

