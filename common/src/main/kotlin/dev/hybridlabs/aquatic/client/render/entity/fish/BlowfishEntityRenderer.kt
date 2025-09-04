package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlowfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.BlowfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BlowfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<BlowfishEntity>(context, BlowfishEntityModel(), true, false)