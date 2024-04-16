package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlueSpottedStingrayEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BlueSpottedStingrayEntityRenderer(context: Context) : HybridAquaticFishEntityRenderer<HybridAquaticFishEntity>(context,
    BlueSpottedStingrayEntityModel(), true, false)

