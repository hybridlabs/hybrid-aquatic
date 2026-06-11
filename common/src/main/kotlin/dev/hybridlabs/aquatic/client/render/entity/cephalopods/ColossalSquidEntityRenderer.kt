package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.ColossalSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.ColossalSquidEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ColossalSquidEntityRenderer(context: Context) :
    HACephalopodEntityRenderer<ColossalSquidEntity>(context, ColossalSquidEntityModel(), true, false)