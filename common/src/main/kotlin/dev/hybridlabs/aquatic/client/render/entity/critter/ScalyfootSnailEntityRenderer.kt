package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.ScalyfootSnailEntityModel
import dev.hybridlabs.aquatic.client.model.entity.critter.SeaSlugEntityModel
import dev.hybridlabs.aquatic.entity.critter.ScalyfootSnailEntity
import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ScalyfootSnailEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<ScalyfootSnailEntity>(context, ScalyfootSnailEntityModel(), true)