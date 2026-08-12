package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.ScalyfootSnailEntityModel
import dev.hybridlabs.aquatic.entity.critter.ScalyfootSnailEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ScalyfootSnailEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<ScalyfootSnailEntity>(context, ScalyfootSnailEntityModel(), true)