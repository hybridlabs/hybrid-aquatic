package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.VampireSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.VampireSquidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class VampireSquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<VampireSquidEntity>(context, VampireSquidEntityModel(), true, true)