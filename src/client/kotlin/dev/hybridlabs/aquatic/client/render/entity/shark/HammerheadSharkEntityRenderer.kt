package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.HammerheadSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.HammerheadSharkEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class HammerheadSharkEntityRenderer(context: Context) : HybridAquaticSharkEntityRenderer<HammerheadSharkEntity>(context, HammerheadSharkEntityModel(), true)