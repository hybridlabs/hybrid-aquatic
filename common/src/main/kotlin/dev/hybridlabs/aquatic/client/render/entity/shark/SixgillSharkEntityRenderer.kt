package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.SixgillSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.SixgillSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SixgillSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<SixgillSharkEntity, LivingEntityRenderState>(context, SixgillSharkEntityModel(), true)