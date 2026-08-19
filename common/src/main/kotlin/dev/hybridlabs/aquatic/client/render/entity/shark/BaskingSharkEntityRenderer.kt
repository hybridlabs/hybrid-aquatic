package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.BaskingSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.BaskingSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BaskingSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<BaskingSharkEntity>(context, BaskingSharkEntityModel(), true)