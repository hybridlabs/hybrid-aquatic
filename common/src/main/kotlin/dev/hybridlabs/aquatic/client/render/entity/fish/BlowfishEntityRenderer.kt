package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlowfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.BlowfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BlowfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<BlowfishEntity>(context, BlowfishEntityModel(), true, false)