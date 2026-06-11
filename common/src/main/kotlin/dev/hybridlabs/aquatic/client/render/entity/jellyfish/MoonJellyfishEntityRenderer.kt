package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.MoonJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.MoonJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MoonJellyfishEntityRenderer(context: Context) :
    HAJellyfishEntityRenderer<MoonJellyfishEntity>(context, MoonJellyfishEntityModel(), true, false)