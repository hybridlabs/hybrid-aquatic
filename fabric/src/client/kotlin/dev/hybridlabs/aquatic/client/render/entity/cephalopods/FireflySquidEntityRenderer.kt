package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.FireflySquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class FireflySquidEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<HybridAquaticCephalopodEntity>(context, FireflySquidEntityModel(), true, true)