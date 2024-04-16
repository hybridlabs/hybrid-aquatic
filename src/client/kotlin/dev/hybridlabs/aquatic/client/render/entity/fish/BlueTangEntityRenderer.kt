package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlueTangEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BlueTangEntityRenderer(context: Context) : HybridAquaticFishEntityRenderer<HybridAquaticFishEntity>(context,
    BlueTangEntityModel(), true, false)