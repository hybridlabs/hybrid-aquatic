package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ParrotfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ParrotfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class ParrotfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<ParrotfishEntity, LivingEntityRenderState>(context, ParrotfishEntityModel(), true, false)