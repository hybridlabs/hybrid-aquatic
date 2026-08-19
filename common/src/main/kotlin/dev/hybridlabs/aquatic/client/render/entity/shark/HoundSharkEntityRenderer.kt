package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.HoundSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HoundSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HoundSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<HoundSharkEntity>(context, HoundSharkEntityModel(), true)