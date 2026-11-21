package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.OceanSunfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.OceanSunfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class OceanSunfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<OceanSunfishEntity, LivingEntityRenderState>(context, OceanSunfishEntityModel(), true, false)