package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ToadfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ToadfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class ToadfishEntityRenderer(context: Context) : HybridAquaticFishEntityRenderer<ToadfishEntity>(context, ToadfishEntityModel(), true, false)