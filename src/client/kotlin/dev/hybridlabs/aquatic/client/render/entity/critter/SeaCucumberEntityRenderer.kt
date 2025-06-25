package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaCucumberEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SeaCucumberEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<SeaCucumberEntity>(context, SeaCucumberEntityModel())