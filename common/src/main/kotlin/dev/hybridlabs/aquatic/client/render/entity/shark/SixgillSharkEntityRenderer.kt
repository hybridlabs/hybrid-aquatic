package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.SixgillSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.SixgillSharkEntity
import dev.hybridlabs.hapi.client.render.entity.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SixgillSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<SixgillSharkEntity>(context, SixgillSharkEntityModel(), true)