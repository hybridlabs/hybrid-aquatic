package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.GiantSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.GiantSquidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GiantSquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<GiantSquidEntity>(context, GiantSquidEntityModel(), true, false)