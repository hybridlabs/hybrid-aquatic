package dev.hybridlabs.aquatic.client.renderer.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.GlowingSuckerOctopusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.GlowingSuckerOctopusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GlowingSuckerOctopusEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<GlowingSuckerOctopusEntity>(
        context,
        GlowingSuckerOctopusEntityModel(),
        true,
        true
    )