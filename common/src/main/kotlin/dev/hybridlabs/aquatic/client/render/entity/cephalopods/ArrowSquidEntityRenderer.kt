package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.ArrowSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.ArrowSquidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class ArrowSquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<ArrowSquidEntity, LivingEntityRenderState>(context, ArrowSquidEntityModel(), true, false)