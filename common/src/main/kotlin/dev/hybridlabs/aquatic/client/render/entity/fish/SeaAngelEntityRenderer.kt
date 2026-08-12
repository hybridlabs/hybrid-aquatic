package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SeaAngelEntityModel
import dev.hybridlabs.aquatic.entity.fish.SeaAngelEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SeaAngelEntityRenderer(context: Context) :
    BaseFishEntityRenderer<SeaAngelEntity>(context, SeaAngelEntityModel(), true, true)