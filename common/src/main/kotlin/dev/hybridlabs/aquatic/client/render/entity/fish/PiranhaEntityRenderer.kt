package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PiranhaEntityModel
import dev.hybridlabs.aquatic.entity.fish.PiranhaEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class PiranhaEntityRenderer(context: Context) :
    BaseFishEntityRenderer<PiranhaEntity, LivingEntityRenderState>(context, PiranhaEntityModel(), true, false)