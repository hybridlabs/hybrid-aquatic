package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlowfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ToadfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ToadfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<ToadfishEntity>(context, BlowfishEntityModel(), true, false)