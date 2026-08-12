package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.HammerheadSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HammerheadSharkEntity
import dev.hybridlabs.hapi.client.render.entity.BaseSharkEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HammerheadSharkEntityRenderer(context: Context) :
    BaseSharkEntityRenderer<HammerheadSharkEntity>(context, HammerheadSharkEntityModel(), true)