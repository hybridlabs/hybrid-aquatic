package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlobfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.BlobfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BlobfishEntityRenderer(context: Context) :
    HAFishEntityRenderer<BlobfishEntity>(context, BlobfishEntityModel(), true, false)
