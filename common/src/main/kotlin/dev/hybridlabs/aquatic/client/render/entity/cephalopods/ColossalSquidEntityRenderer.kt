package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.ColossalSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.ColossalSquidEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ColossalSquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<ColossalSquidEntity>(context, ColossalSquidEntityModel(), true, false)