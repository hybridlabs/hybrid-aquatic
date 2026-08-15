package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.BullSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.BullSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BullSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<BullSharkEntity>(context, BullSharkEntityModel(), true)