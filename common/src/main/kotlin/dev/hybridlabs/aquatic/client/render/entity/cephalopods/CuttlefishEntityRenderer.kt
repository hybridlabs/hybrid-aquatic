package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.CuttlefishEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CuttlefishEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<CuttlefishEntity>(context, CuttlefishEntityModel(), true, false)