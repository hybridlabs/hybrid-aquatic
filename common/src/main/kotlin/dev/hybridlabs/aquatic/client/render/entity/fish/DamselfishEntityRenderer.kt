package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DamselfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.DamselfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DamselfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<DamselfishEntity>(context, DamselfishEntityModel(), true, false)