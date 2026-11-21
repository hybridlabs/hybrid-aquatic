package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BarracudaEntityModel
import dev.hybridlabs.aquatic.entity.fish.BarracudaEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class BarracudaEntityRenderer(context: Context) :
    BaseFishEntityRenderer<BarracudaEntity, LivingEntityRenderState>(context, BarracudaEntityModel(), true, false)