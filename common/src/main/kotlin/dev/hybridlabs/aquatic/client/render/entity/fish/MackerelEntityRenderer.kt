package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MackerelEntityModel
import dev.hybridlabs.aquatic.entity.fish.MackerelEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class MackerelEntityRenderer(context: Context) :
    BaseFishEntityRenderer<MackerelEntity, LivingEntityRenderState>(context, MackerelEntityModel(), true, false)