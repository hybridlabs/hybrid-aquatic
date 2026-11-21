package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.FrilledSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.FrilledSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class FrilledSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<FrilledSharkEntity, LivingEntityRenderState>(context, FrilledSharkEntityModel(), true)