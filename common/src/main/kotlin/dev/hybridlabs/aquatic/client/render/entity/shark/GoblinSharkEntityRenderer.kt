package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.GoblinSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.GoblinSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GoblinSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<GoblinSharkEntity>(context, GoblinSharkEntityModel(), true)