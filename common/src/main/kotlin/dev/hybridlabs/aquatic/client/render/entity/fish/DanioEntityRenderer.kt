package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.DanioEntityModel
import dev.hybridlabs.aquatic.entity.fish.DanioEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DanioEntityRenderer(context: Context)
    : HAFishEntityRenderer<DanioEntity>(context, DanioEntityModel(), false, false)