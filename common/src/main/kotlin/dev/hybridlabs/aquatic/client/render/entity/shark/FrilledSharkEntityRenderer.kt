package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.FrilledSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.FrilledSharkEntity
import dev.hybridlabs.hapi.client.render.entity.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FrilledSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<FrilledSharkEntity>(context, FrilledSharkEntityModel(), true)