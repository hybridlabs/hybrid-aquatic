package dev.hybridlabs.aquatic.client.renderer.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.CuttlefishEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CuttlefishEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<CuttlefishEntity>(context, CuttlefishEntityModel(), true, false)