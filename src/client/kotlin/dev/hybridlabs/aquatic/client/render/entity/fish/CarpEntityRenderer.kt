package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CarpEntityModel
import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CarpEntityRenderer(context: Context) : HybridAquaticFishEntityRenderer<CarpEntity>(context,
    CarpEntityModel(), true, false)