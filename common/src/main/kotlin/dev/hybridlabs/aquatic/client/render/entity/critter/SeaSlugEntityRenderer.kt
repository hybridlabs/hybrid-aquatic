package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaSlugEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SeaSlugEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<SeaSlugEntity, LivingEntityRenderState>(context, SeaSlugEntityModel(), true)