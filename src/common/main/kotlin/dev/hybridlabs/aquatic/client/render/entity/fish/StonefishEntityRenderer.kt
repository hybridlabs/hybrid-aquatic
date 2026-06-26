package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.StonefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.StonefishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class StonefishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<StonefishEntity>(context, StonefishEntityModel(), true, false)

