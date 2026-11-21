package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.ColossalSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.ColossalSquidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class ColossalSquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<ColossalSquidEntity, LivingEntityRenderState>(context, ColossalSquidEntityModel(), true, false)