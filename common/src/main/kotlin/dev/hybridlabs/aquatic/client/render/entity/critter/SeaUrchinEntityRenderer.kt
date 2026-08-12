package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.SeaUrchinEntityModel
import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCritterEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaUrchinEntityRenderer(context: Context) :
    BaseCritterEntityRenderer<SeaUrchinEntity>(context, SeaUrchinEntityModel(), true)