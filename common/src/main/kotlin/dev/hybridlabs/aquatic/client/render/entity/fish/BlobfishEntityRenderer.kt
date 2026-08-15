package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BlobfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.BlobfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BlobfishEntityRenderer(context: Context) :
    BaseFishEntityRenderer<BlobfishEntity>(context, BlobfishEntityModel(), true, false)
