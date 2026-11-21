package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeaBassEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeaBassEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SeaBassEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SeaBassEntity, LivingEntityRenderState>(context, SeaBassEntityModel(), true, false)