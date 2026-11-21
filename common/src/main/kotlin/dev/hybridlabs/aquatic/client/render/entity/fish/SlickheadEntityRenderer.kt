package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SlickheadEntityModel
import dev.hybridlabs.aquatic.entity.fish.SlickheadEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SlickheadEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SlickheadEntity, LivingEntityRenderState>(context, SlickheadEntityModel(), true, false)