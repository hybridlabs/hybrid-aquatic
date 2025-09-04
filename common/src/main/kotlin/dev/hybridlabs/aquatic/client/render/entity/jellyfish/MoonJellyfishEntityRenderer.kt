package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.MoonJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.MoonJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class MoonJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<MoonJellyfishEntity>(context, MoonJellyfishEntityModel(), true, false)