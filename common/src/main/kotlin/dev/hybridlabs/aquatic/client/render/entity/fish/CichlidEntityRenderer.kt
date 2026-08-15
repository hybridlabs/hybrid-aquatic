package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CichlidEntityModel
import dev.hybridlabs.aquatic.entity.fish.CichlidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CichlidEntityRenderer(context: Context) :
    BaseFishEntityRenderer<CichlidEntity>(context, CichlidEntityModel(), true, false)