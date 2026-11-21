package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.RockfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.RockfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class RockfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<RockfishEntity, LivingEntityRenderState>(context, RockfishEntityModel(), true, false)