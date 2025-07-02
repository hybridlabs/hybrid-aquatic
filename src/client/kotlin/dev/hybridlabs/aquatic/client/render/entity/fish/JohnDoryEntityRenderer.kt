package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.JohnDoryEntityModel
import dev.hybridlabs.aquatic.entity.fish.JohnDoryEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class JohnDoryEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<JohnDoryEntity>(context, JohnDoryEntityModel(), true, false)
