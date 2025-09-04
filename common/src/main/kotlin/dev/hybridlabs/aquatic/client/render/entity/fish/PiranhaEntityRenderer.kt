package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PiranhaEntityModel
import dev.hybridlabs.aquatic.entity.fish.PiranhaEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class PiranhaEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<PiranhaEntity>(context, PiranhaEntityModel(), true, false)