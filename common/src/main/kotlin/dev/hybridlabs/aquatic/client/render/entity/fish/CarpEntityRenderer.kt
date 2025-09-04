package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CarpEntityModel
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CarpEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<CarpEntity>(context, CarpEntityModel(), true, false)