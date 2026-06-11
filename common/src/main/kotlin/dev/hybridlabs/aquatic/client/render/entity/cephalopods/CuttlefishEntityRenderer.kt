package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.CuttlefishEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CuttlefishEntityRenderer(context: Context) :
    HACephalopodEntityRenderer<CuttlefishEntity>(context, CuttlefishEntityModel(), true, false)