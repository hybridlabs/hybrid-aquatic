package dev.hybridlabs.aquatic.client.renderer.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.VampireSquidEntityModel
import dev.hybridlabs.aquatic.client.render.entity.cephalopods.HybridAquaticCephalopodEntityRenderer
import dev.hybridlabs.aquatic.entity.cephalopod.VampireSquidEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class VampireSquidEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<VampireSquidEntity>(context, VampireSquidEntityModel(), true, true)