package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.HatchetfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HatchetfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HatchetfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<HatchetfishEntity>(context, HatchetfishEntityModel(), true, false)
