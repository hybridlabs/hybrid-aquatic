package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaUrchinEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SeaUrchinEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<SeaUrchinEntity, LivingEntityRenderState>(context, SeaUrchinEntityModel(), true)