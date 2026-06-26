package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.ClownfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ClownfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<ClownfishEntity>(context, ClownfishEntityModel(), true, false)