package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.LanternSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.LanternSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class LanternSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<LanternSharkEntity, LivingEntityRenderState>(context, LanternSharkEntityModel(), true, true)