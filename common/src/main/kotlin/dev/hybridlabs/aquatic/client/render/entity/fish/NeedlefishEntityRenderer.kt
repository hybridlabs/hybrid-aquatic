package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.NeedlefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.NeedlefishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class NeedlefishEntityRenderer(context: Context) :
    HAFishEntityRenderer<NeedlefishEntity>(context, NeedlefishEntityModel(), true, false)