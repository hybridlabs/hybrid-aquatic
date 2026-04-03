package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ViperfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ViperfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ViperfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<ViperfishEntity>(context, ViperfishEntityModel(), true, false)
