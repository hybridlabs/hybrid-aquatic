package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.FlashlightFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class FlashlightFishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<FlashlightFishEntity>(context, FlashlightFishEntityModel(), true, true)