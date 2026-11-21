package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.WrasseEntityModel
import dev.hybridlabs.aquatic.entity.fish.WrasseEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class WrasseEntityRenderer(context: Context) :
    BaseFishEntityRenderer<WrasseEntity, LivingEntityRenderState>(context, WrasseEntityModel(), true, false)