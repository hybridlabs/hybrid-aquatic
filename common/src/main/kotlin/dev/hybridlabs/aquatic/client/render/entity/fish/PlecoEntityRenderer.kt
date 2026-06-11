package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PlecoEntityModel
import dev.hybridlabs.aquatic.entity.fish.PlecoEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PlecoEntityRenderer(context: Context) :
    HAFishEntityRenderer<PlecoEntity>(context, PlecoEntityModel(), true, false)
