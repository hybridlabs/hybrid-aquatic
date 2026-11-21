package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.HoundSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class HoundSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<HoundSharkEntity, LivingEntityRenderState>(context, HoundSharkEntityModel(), true)