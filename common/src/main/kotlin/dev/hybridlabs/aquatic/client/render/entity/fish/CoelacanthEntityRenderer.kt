package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CoelacanthEntityModel
import dev.hybridlabs.aquatic.entity.fish.CoelacanthEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CoelacanthEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<CoelacanthEntity>(context, CoelacanthEntityModel(), true, false)