package dev.hybridlabs.aquatic.client.renderer.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.UmbrellaOctopusEntityModel
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.HybridAquaticCephalopodEntityRenderer
import dev.hybridlabs.aquatic.entity.cephalopod.UmbrellaOctopusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class UmbrellaOctopusEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<UmbrellaOctopusEntity>(context, UmbrellaOctopusEntityModel(), true, false)