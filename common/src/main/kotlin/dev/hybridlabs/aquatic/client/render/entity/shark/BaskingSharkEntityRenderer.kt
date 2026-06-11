package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.BaskingSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.BaskingSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BaskingSharkEntityRenderer(context: Context) :
    HASharkEntityRenderer<BaskingSharkEntity>(context, BaskingSharkEntityModel(), true)