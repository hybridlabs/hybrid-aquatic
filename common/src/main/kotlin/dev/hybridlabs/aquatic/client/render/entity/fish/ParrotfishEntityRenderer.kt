package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ParrotfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ParrotfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ParrotfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<ParrotfishEntity>(context, ParrotfishEntityModel(), true, false)