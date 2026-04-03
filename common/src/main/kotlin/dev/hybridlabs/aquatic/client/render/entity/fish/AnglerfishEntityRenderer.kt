package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.AnglerfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.AnglerfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class AnglerfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<AnglerfishEntity>(context, AnglerfishEntityModel(), true, true)
