package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SlickheadEntityModel
import dev.hybridlabs.aquatic.entity.fish.SlickheadEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SlickheadEntityRenderer(context: Context) :
    HAFishEntityRenderer<SlickheadEntity>(context, SlickheadEntityModel(), true, false)