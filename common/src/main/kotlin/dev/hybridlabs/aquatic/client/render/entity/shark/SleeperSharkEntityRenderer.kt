package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.SleeperSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.SleeperSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SleeperSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<SleeperSharkEntity, LivingEntityRenderState>(context, SleeperSharkEntityModel(), true)