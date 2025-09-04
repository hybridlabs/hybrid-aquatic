package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.NeedlefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.NeedlefishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class NeedlefishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<NeedlefishEntity>(context, NeedlefishEntityModel(), true, false)