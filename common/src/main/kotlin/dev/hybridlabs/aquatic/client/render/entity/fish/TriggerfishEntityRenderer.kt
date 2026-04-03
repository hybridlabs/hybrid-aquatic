package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TriggerfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.TriggerfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TriggerfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<TriggerfishEntity>(context, TriggerfishEntityModel(), true, false)