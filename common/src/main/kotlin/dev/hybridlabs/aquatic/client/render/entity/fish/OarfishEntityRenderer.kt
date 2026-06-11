package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.OarfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.OarfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class OarfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<OarfishEntity>(context, OarfishEntityModel(), true, true)