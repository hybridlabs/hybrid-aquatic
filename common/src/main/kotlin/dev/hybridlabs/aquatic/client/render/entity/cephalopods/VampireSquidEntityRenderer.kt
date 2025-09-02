package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.VampireSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class VampireSquidEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<HybridAquaticCephalopodEntity>(context, VampireSquidEntityModel(), true, true)