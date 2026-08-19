package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaCucumberEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaCucumberEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<SeaCucumberEntity>(context, SeaCucumberEntityModel(), true)