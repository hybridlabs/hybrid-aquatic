package dev.hybridlabs.aquatic.client.renderer.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaCucumberEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaCucumberEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<SeaCucumberEntity>(context, SeaCucumberEntityModel(), true)