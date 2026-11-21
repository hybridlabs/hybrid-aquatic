package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TetraEntityModel
import dev.hybridlabs.aquatic.entity.fish.TetraEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class TetraEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TetraEntity, LivingEntityRenderState>(context, TetraEntityModel(), false, false)
