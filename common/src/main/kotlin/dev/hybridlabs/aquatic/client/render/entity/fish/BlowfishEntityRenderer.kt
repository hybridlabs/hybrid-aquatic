package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlowfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.BlowfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BlowfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<BlowfishEntity>(context, BlowfishEntityModel(), true, false)