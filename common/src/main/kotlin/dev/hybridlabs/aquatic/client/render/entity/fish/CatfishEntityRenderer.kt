package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CatfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.CatfishEntity
import dev.hybridlabs.aquatic.entity.fish.TroutEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CatfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<CatfishEntity>(context, CatfishEntityModel(), true, false)
