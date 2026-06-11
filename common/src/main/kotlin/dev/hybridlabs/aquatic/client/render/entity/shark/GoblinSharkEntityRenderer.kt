package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.GoblinSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.GoblinSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GoblinSharkEntityRenderer(context: Context) :
    HASharkEntityRenderer<GoblinSharkEntity>(context, GoblinSharkEntityModel(), true)