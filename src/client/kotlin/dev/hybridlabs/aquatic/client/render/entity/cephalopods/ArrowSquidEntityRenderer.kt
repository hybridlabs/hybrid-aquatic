package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.ArrowSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.ArrowSquidEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class ArrowSquidEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<ArrowSquidEntity>(context, ArrowSquidEntityModel(), true, false)