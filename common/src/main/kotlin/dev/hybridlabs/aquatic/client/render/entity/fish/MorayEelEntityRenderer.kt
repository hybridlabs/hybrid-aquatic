package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MorayEelEntityModel
import dev.hybridlabs.aquatic.entity.fish.MorayEelEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class MorayEelEntityRenderer(context: Context) :
    BaseFishEntityRenderer<MorayEelEntity, LivingEntityRenderState>(context, MorayEelEntityModel(), true, false)