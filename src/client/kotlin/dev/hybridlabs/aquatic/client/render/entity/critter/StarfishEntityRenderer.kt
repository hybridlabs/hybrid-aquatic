package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.StarfishEntityModel
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class StarfishEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<StarfishEntity>(context, StarfishEntityModel())