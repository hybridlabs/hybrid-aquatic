package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.LanternSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.LanternSharkEntity
import dev.hybridlabs.hapi.client.render.entity.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LanternSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<LanternSharkEntity>(context, LanternSharkEntityModel(), true, true)