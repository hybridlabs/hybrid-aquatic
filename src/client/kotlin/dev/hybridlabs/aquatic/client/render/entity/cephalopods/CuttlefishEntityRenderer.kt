package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.CuttlefishEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CuttlefishEntityRenderer(context: Context) : HybridAquaticCephalopodEntityRenderer<HybridAquaticCephalopodEntity>(context, CuttlefishEntityModel(), true, false)

