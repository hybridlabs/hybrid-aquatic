package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.WrasseEntityModel
import dev.hybridlabs.aquatic.entity.fish.WrasseEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class WrasseEntityRenderer(context: Context) :
    HAFishEntityRenderer<WrasseEntity>(context, WrasseEntityModel(), true, false)