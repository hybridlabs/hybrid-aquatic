package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.JohnDoryEntityModel
import dev.hybridlabs.aquatic.entity.fish.JohnDoryEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class JohnDoryEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<JohnDoryEntity>(context, JohnDoryEntityModel(), true, false)
