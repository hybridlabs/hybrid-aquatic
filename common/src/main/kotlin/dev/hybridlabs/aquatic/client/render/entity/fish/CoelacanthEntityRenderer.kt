package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CoelacanthEntityModel
import dev.hybridlabs.aquatic.entity.fish.CoelacanthEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CoelacanthEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<CoelacanthEntity>(context, CoelacanthEntityModel(), true, false)