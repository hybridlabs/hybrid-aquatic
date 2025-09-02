package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.UmbrellaOctopusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class UmbrellaOctopusEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<HybridAquaticCephalopodEntity>(
        context,
        UmbrellaOctopusEntityModel(),
        true,
        false
    )