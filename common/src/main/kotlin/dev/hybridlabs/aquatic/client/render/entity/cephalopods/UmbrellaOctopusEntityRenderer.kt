package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.UmbrellaOctopusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.UmbrellaOctopusEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class UmbrellaOctopusEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<UmbrellaOctopusEntity>(context, UmbrellaOctopusEntityModel(), true, false)