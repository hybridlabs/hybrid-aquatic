package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaCucumberEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaCucumberEntityRenderer(context: Context) :
    HACritterEntityRenderer<SeaCucumberEntity>(context, SeaCucumberEntityModel(), true)