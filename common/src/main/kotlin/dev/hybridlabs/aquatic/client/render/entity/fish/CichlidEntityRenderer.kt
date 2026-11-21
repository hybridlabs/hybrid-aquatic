package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CichlidEntityModel
import dev.hybridlabs.aquatic.entity.fish.CichlidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class CichlidEntityRenderer(context: Context) :
    BaseFishEntityRenderer<CichlidEntity, LivingEntityRenderState>(context, CichlidEntityModel(), true, false)