package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PearlfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.PearlfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PearlfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<PearlfishEntity>(context, PearlfishEntityModel(), true, false)