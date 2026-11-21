package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.ScalyfootSnailEntityModel
import dev.hybridlabs.aquatic.entity.critter.ScalyfootSnailEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class ScalyfootSnailEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<ScalyfootSnailEntity, LivingEntityRenderState>(context, ScalyfootSnailEntityModel(), true)