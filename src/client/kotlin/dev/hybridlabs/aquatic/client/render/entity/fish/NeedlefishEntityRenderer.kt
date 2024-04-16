package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.NeedlefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class NeedlefishEntityRenderer(context: Context) : HybridAquaticFishEntityRenderer<HybridAquaticFishEntity>(context,
    NeedlefishEntityModel(), true, false)