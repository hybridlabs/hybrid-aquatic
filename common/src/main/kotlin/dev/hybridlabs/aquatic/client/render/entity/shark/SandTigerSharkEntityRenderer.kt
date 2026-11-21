package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.SandTigerSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.SandTigerSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SandTigerSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<SandTigerSharkEntity, LivingEntityRenderState>(context, SandTigerSharkEntityModel(), true)