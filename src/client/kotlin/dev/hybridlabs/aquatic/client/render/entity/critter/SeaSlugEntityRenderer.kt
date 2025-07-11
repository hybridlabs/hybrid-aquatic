package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaSlugEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaSlugEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class SeaSlugEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<SeaSlugEntity>(context, SeaSlugEntityModel())