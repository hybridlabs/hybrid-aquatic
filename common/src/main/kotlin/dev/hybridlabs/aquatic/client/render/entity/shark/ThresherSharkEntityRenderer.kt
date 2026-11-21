package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.ThresherSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.ThresherSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class ThresherSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<ThresherSharkEntity, LivingEntityRenderState>(context, ThresherSharkEntityModel(), true)