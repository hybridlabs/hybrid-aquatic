package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.ArrowSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ArrowSquidEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<HybridAquaticCephalopodEntity>(context, ArrowSquidEntityModel(), true, false)