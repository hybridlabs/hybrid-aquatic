package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.HammerheadSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HammerheadSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class HammerheadSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<HammerheadSharkEntity, LivingEntityRenderState>(context, HammerheadSharkEntityModel(), true)