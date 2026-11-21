package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.NautilusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.NautilusEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class NautilusEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<NautilusEntity, LivingEntityRenderState>(context, NautilusEntityModel(), true, false)