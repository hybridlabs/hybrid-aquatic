package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.OpahEntityModel
import dev.hybridlabs.aquatic.entity.fish.OpahEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class OpahEntityRenderer(context: Context) :
    BaseFishEntityRenderer<OpahEntity, LivingEntityRenderState>(context, OpahEntityModel(), true, false)