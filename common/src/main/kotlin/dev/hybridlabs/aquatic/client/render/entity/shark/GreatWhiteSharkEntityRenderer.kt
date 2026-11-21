package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.GreatWhiteSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.GreatWhiteSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class GreatWhiteSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<GreatWhiteSharkEntity, LivingEntityRenderState>(context, GreatWhiteSharkEntityModel(), true, false)