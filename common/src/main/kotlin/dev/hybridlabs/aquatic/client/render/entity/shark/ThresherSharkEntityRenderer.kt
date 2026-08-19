package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.ThresherSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.ThresherSharkEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ThresherSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<ThresherSharkEntity>(context, ThresherSharkEntityModel(), true)