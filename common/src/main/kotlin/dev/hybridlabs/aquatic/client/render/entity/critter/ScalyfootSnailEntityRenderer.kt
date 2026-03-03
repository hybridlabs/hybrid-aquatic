package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.ScalyfootSnailEntityModel
import dev.hybridlabs.aquatic.entity.critter.ScalyfootSnailEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ScalyfootSnailEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<ScalyfootSnailEntity>(context, ScalyfootSnailEntityModel(), true)