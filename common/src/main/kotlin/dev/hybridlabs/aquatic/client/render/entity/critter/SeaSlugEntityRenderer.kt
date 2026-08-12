package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaSlugEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaSlugEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<SeaSlugEntity>(context, SeaSlugEntityModel(), true)