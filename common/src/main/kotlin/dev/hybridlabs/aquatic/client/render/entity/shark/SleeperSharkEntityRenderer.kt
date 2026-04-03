package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.SleeperSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.SleeperSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SleeperSharkEntityRenderer(context: Context) :
    HASharkEntityRenderer<SleeperSharkEntity>(context, SleeperSharkEntityModel(), true)