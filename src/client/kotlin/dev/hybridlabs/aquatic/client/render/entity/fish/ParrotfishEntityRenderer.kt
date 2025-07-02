package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ParrotfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ParrotfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class ParrotfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<ParrotfishEntity>(context, ParrotfishEntityModel(), true, false)