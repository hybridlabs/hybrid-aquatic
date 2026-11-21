package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.CuttlefishEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.CuttlefishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class CuttlefishEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<CuttlefishEntity, LivingEntityRenderState>(context, CuttlefishEntityModel(), true, false)