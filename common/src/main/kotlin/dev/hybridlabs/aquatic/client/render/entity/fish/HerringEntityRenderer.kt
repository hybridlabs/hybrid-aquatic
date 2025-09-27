package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.HerringEntityModel
import dev.hybridlabs.aquatic.entity.fish.HerringEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HerringEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<HerringEntity>(context, HerringEntityModel(), true, false)