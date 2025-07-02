package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.VampireSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.VampireSquidEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class VampireSquidEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<VampireSquidEntity>(context, VampireSquidEntityModel(), true, true)