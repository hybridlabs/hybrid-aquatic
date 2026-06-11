package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CichlidEntityModel
import dev.hybridlabs.aquatic.entity.fish.CichlidEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CichlidEntityRenderer(context: Context) :
    HAFishEntityRenderer<CichlidEntity>(context, CichlidEntityModel(), true, false)