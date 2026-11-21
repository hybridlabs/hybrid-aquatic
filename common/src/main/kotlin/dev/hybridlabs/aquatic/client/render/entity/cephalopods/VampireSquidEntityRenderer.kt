package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.VampireSquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.VampireSquidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class VampireSquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<VampireSquidEntity, LivingEntityRenderState>(context, VampireSquidEntityModel(), true, true)