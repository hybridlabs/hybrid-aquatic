package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.GoldfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.GoldfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class GoldfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<GoldfishEntity, LivingEntityRenderState>(context, GoldfishEntityModel(), true, false)