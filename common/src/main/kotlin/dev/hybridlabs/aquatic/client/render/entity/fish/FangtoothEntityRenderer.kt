package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.FangtoothEntityModel
import dev.hybridlabs.aquatic.entity.fish.FangtoothEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FangtoothEntityRenderer(context: Context) :
    BaseFishEntityRenderer<FangtoothEntity>(context, FangtoothEntityModel(), true, false)
