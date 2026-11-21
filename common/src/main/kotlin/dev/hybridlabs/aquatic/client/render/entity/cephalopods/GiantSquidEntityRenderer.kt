package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.GiantSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.GiantSquidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class GiantSquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<GiantSquidEntity, LivingEntityRenderState>(context, GiantSquidEntityModel(), true, false)