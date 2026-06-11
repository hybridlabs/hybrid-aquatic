package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.client.model.entity.shark.SandTigerSharkEntityModel
import dev.hybridlabs.aquatic.entity.shark.SandTigerSharkEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SandTigerSharkEntityRenderer(context: Context) :
    HASharkEntityRenderer<SandTigerSharkEntity>(context, SandTigerSharkEntityModel(), true)